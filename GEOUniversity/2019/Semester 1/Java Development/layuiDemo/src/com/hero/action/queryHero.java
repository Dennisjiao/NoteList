package com.hero.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hero.dao.HeroDao;
import com.hero.factory.DAOFactory;
import com.hero.pojo.Hero;

/**
 * 查询好汉servlet
 */
@WebServlet(name = "queryHeroServlet",urlPatterns = {"/queryHero"})
public class queryHero extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String sex=request.getParameter("sex");		
		String description=request.getParameter("description");	
		
		Hero hero =new Hero();
		if(age!=null && !age.equals("")){
			hero.setAge(Integer.valueOf(age));
		}
		hero.setName(name);
		hero.setDescription(description);		
		hero.setSex(sex);
		try {
			HeroDao heroDao=DAOFactory.getDAOInstance();
			List<Hero> list=heroDao.findByCriteria(hero);
			request.setAttribute("herolist", list);
			request.setAttribute("queryParam", hero);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("notifymsg", "查询好汉失败！");
		}	
		
		request.getRequestDispatcher("query.jsp").forward(request, response);
	}


}
