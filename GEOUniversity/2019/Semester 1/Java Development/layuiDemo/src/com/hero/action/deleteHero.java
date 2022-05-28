package com.hero.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hero.dao.HeroDao;
import com.hero.factory.DAOFactory;
import com.hero.pojo.Hero;
/**
 * 删除好汉servlet
 */
@WebServlet(name = "deleteHeroServlet",urlPatterns = {"/deleteHero"})
public class deleteHero extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		
		try {
			HeroDao heroDao=DAOFactory.getDAOInstance();
			boolean flag=heroDao.delete(Integer.valueOf(id));
			heroDao=DAOFactory.getDAOInstance();
			List<Hero> list=heroDao.findAll();
			request.setAttribute("herolist", list);
			request.setAttribute("queryParam", null);
			if(flag){
				request.setAttribute("notifymsg", "删除好汉成功！");
			}
			else{
				request.setAttribute("notifymsg", "删除好汉失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("notifymsg", "删除好汉失败！");
		}	
		
		request.getRequestDispatcher("query.jsp").forward(request, response);
	}
}
