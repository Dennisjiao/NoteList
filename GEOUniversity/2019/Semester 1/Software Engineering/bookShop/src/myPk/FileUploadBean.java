package myPk;


import java.io.*; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.ServletInputStream; 
import javax.servlet.ServletException; 

public class FileUploadBean {
	private static String newline = "\r\n";        //�趨���з�
	private String uploadDirectory = ".";        //Ĭ�ϵı���λ��
	private String ContentType = "";	         //�ļ�����
	private String CharacterEncoding = "";      //�����ʽ
	private String sFileName="";				//�ļ���
	private String sFullFileName="";				//��β׺���ļ���

	//�趨�ļ�Ҫ��������֣�����β׺��
	public void setFileName(String s){
		sFileName=s;
	}
	
	//��ȡ��β׺���ļ���
	public String getFullFileName(){
		return sFullFileName;
	}
	
	//�趨�ļ�Ҫ�����ڷ������е�λ��
	public void setUploadDirectory(String s){ 
		uploadDirectory = s; 
	} 
	//��ȡ�ļ������������ǰ��û��ϴ����ļ�����ԭ������
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

	//�õ�content-type
	public void setContentType(String s){ 
		ContentType = s; 
		int j; 
		if((j = ContentType.indexOf("boundary=")) != -1){ 
			ContentType = ContentType.substring(j + 9); 
			ContentType = "--" + ContentType; 
		} 
	} 
	//�õ��ļ������ʽ
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
		//�õ��ļ��� 
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

			//�������ļ����ļ���� 
			File file = new File(uploadDirectory, filename); 

			//�����������ļ�������� 
			FileOutputStream fileoutputstream = new FileOutputStream(file); 

			while((sContentType = readLine(Linebyte, ai, servletinputstream, CharacterEncoding)) != null){ 
				if(sContentType.indexOf(ContentType) == 0 && Linebyte[0] == 45) 
					break; 

				if(s5 != null){ 
					//д�����ļ�
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

	//readLine�����ѱ��ύ���������ݰ��ж�ȡ
	private String readLine(byte Linebyte[], int ai[],ServletInputStream servletinputstream,String CharacterEncoding){ 
		try{ //��ȡһ��
			ai[0] = servletinputstream.readLine(Linebyte, 0, Linebyte.length); 
			if(ai[0] == -1) 
				return null; 
		}catch(IOException ex){ 
			return null; 
		} 
		try{ 
			if(CharacterEncoding == null){ 
				//��Ĭ�ϵı��뷽ʽ�Ѹ�����byte����ת��Ϊ�ַ��� 
				return new String(Linebyte, 0, ai[0]); 
			}else{ 
				//�ø����ı��뷽ʽ�Ѹ�����byte����ת��Ϊ�ַ��� 
				return new String(Linebyte, 0, ai[0], CharacterEncoding); 
			} 
		}catch(Exception ex){ 
			return null; 
		} 
	} 
}
