package myPk;


import java.io.*; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.ServletInputStream; 
import javax.servlet.ServletException; 

public class FileUploadBean {
	private static String newline = "\r\n";        //设定换行符
	private String uploadDirectory = ".";        //默认的保存位置
	private String ContentType = "";	         //文件类型
	private String CharacterEncoding = "";      //编码格式
	private String sFileName="";				//文件名
	private String sFullFileName="";				//带尾缀的文件名

	//设定文件要保存的名字（不含尾缀）
	public void setFileName(String s){
		sFileName=s;
	}
	
	//获取带尾缀的文件名
	public String getFullFileName(){
		return sFullFileName;
	}
	
	//设定文件要保存在服务器中的位置
	public void setUploadDirectory(String s){ 
		uploadDirectory = s; 
	} 
	//提取文件名，本方法是把用户上传的文件按照原名保存
	private String getFileName(String s){ 
		String tem=null;
		int i = s.lastIndexOf("\\"); 
		if(i < 0 || i >= s.length() - 1){ 
			i = s.lastIndexOf("/"); 
			if(i < 0 || i >= s.length() - 1) 
				tem=s; 
		} 
		else
			tem=s.substring(i + 1);
		if(sFileName!="")
		{ 
			tem=sFileName+s.substring(s.lastIndexOf("."));
		}
		sFullFileName=tem;
		return tem;
	} 

	//得到content-type
	public void setContentType(String s){ 
		ContentType = s; 
		int j; 
		if((j = ContentType.indexOf("boundary=")) != -1){ 
			ContentType = ContentType.substring(j + 9); 
			ContentType = "--" + ContentType; 
		} 
	} 
	//得到文件编码格式
	public void setCharacterEncoding(String s){ 
		CharacterEncoding = s; 
	} 
	
	public void uploadFile( HttpServletRequest req) throws ServletException, IOException{ 
		setCharacterEncoding(req.getCharacterEncoding()); 
		setContentType(req.getContentType()); 
		uploadFile(req.getInputStream()); 
	} 

	public void uploadFile( ServletInputStream servletinputstream) throws ServletException, IOException{ 

		String s5 = null; 
		String filename = null; 
		byte Linebyte[] = new byte[4096]; 
		byte outLinebyte[] = new byte[4096]; 
		int ai[] = new int[1]; 
		int ai1[] = new int[1]; 

		String line; 
		//得到文件名 
		while((line = readLine(Linebyte, ai, servletinputstream, CharacterEncoding)) != null){ 
			int i = line.indexOf("filename="); 
			if(i >= 0){ 
				line = line.substring(i + 10); 
				if((i = line.indexOf("\"")) > 0) 
					line = line.substring(0, i); 
				break; 
			} 
		} 

		filename = line; 

		if(filename != null && !filename.equals("\"")){ 
			filename = getFileName(filename); 

			String sContentType = readLine(Linebyte, ai, servletinputstream, CharacterEncoding); 
			if(sContentType.indexOf("Content-Type") >= 0) 
				readLine(Linebyte, ai, servletinputstream, CharacterEncoding); 

			//建立新文件的文件句柄 
			File file = new File(uploadDirectory, filename); 

			//建立生成新文件的输出流 
			FileOutputStream fileoutputstream = new FileOutputStream(file); 

			while((sContentType = readLine(Linebyte, ai, servletinputstream, CharacterEncoding)) != null){ 
				if(sContentType.indexOf(ContentType) == 0 && Linebyte[0] == 45) 
					break; 

				if(s5 != null){ 
					//写入新文件
					fileoutputstream.write(outLinebyte, 0, ai1[0]); 
					fileoutputstream.flush(); 
				} 
				s5 = readLine(outLinebyte, ai1, servletinputstream, CharacterEncoding); 
				if(s5 == null || s5.indexOf(ContentType) == 0 && outLinebyte[0] == 45) 
				break; 
				fileoutputstream.write(Linebyte, 0, ai[0]); 
				fileoutputstream.flush(); 
			} 

			byte byte0; 
			if(newline.length() == 1) 
			byte0 = 2; 
			else 
			byte0 = 1; 
			if(s5 != null && outLinebyte[0] != 45 && ai1[0] > newline.length() * byte0) 
			fileoutputstream.write(outLinebyte, 0, ai1[0] - newline.length() * byte0); 
			if(sContentType != null && Linebyte[0] != 45 && ai[0] > newline.length() * byte0) 
			fileoutputstream.write(Linebyte, 0, ai[0] - newline.length() * byte0); 
			fileoutputstream.close(); 
		} 
	} 

	//readLine函数把表单提交上来的数据按行读取
	private String readLine(byte Linebyte[], int ai[],ServletInputStream servletinputstream,String CharacterEncoding){ 
		try{ //读取一行
			ai[0] = servletinputstream.readLine(Linebyte, 0, Linebyte.length); 
			if(ai[0] == -1) 
				return null; 
		}catch(IOException ex){ 
			return null; 
		} 
		try{ 
			if(CharacterEncoding == null){ 
				//用默认的编码方式把给定的byte数组转换为字符串 
				return new String(Linebyte, 0, ai[0]); 
			}else{ 
				//用给定的编码方式把给定的byte数组转换为字符串 
				return new String(Linebyte, 0, ai[0], CharacterEncoding); 
			} 
		}catch(Exception ex){ 
			return null; 
		} 
	} 
}
