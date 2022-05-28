package com.eshore;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
	urlPatterns = { "/servletContextDemo.do" }, 
	loadOnStartup = 0, 
	name = "ServletContextDemo", 
	displayName = "demo", 
	initParams = { 
		@WebInitParam(name = "dir", value = "/dir"), 
		@WebInitParam(name = "success", value = "success.html"),
		@WebInitParam(name = "resourcePath", value = "/dir/test.txt")
	}
)
public class ServletContextDemo extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dir = getInitParameter("dir");
		String success = getInitParameter("success");
		String resourcePath = getInitParameter("resourcePath");
		//获取ServletContext对象   
		ServletContext context =  getServletContext();
		//getRealPath获得真实路径
		String path = context.getRealPath(success);
		System.out.println("path真实路径-----"+path);
		//getResourcePaths获得指定路径的内容
		Set set = context.getResourcePaths(dir);
		for(Object str:set){
			System.out.println("文件内容-----"+(String)str);
		}
		//获得服务器版本
		String serverInfo = context.getServerInfo();
		System.out.println("获得服务器版本-----"+serverInfo);
		
		//getResourceAsStream获得资源文件内容
		path = context.getRealPath(resourcePath);
		File file = new File(path);
        String filename = file.getName();// 获取日志文件名称
		InputStream in = new BufferedInputStream(context.getResourceAsStream(resourcePath));
		byte[] buffer = new byte[in.available()];
        in.read(buffer);
        in.close();
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
        response.addHeader("Content-Length", "" + file.length());
		OutputStream out =new BufferedOutputStream( response.getOutputStream());
		response.setContentType("application/octet-stream");
		out.write(buffer);// 输出文件
		out.flush();
		out.close();
	}
	
	public void init() throws ServletException {
		 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}
	public ServletContextDemo() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
}
