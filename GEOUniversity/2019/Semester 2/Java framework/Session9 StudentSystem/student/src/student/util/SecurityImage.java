package student.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class SecurityImage {
	
	//将图片转换成字节流并返回
	public static ByteArrayInputStream getImageAsInputStream(String securityCode) throws IOException
	{
	
		BufferedImage image=createImage(securityCode);
		return convertImageToStream(image);
	}
	//取随机颜色
	private static Color getColor(){
		Random random = new Random();
		int r = random.nextInt(256);//0-255
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		return new Color(r,g,b);
	}
	//生成验证验图片
	public static BufferedImage createImage(String securityCode)
	{
		//验证码长度
		int	codeLength = securityCode.length();
		//字体大小
		int fSize=20;		
		//图片宽度
		int width=100;
		//图片高度
		int height=30;
		
		//System.out.println("width="+width+"height="+height);
		//图片
		BufferedImage image=new BufferedImage(width,height,	BufferedImage.TYPE_INT_RGB);
		Graphics g=image.createGraphics();
		
		//设置背景色
		g.setColor(Color.WHITE);
		//填充背景
		g.fillRect(0,0,width,height);
		
		//设置边框颜色
		g.setColor(Color.DARK_GRAY);
		//边框字体样式
		g.setFont(new Font("Arial",Font.BOLD,height-2));
		//绘制边框
		g.drawRect(0,0,width-1,height-1);
		
		//绘制噪点
		Random rand=new Random();
		//设置噪点颜色
		g.setColor(Color.LIGHT_GRAY);
		for(int i=0;i<codeLength*6;i++)
		{
			g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			g.fillOval((int)(Math.random()*width),(int)(Math.random()*height),2,2);//圆直径为2			
		}
		for (int i = 0; i < 10; i++) {			
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			int xl = rand.nextInt(x+10);
			int yl = rand.nextInt(y+10);
			g.setColor(getColor());
			g.drawLine(x, y, x + xl, y + yl);
		}
		//绘制验证码
		int codeY=2*height/3;
		//设置字体颜色和样式		
		g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		g.setFont(new Font("",Font.ITALIC,height));		
		for(int i=0;i<codeLength;i++){			
			g.drawString(String.valueOf(securityCode.charAt(i)),i*fSize+7,codeY+5);
		}
		//关闭资源
		g.dispose();
		
		return image;
	}	
	//把图片转换成流
	private static ByteArrayInputStream convertImageToStream(BufferedImage image) throws IOException
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpeg", outputStream);        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());	
		outputStream.close();
		inputStream.close();
		
		return inputStream;
	}

}
