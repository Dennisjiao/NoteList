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
 * 添加好汉servlet
 */
@WebServlet(name = "updateHeroServlet",urlPatterns = {"/updateHero"})
public class updateHero extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");		
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String description=request.getParameter("description");	
		if(name==null || name.equals("")){
			try {
				HeroDao heroDao=DAOFactory.getDAOInstance();
				Hero hero=heroDao.findByHeroId(Integer.valueOf(id));
				request.setAttribute("hero", hero);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("edit.jsp").forward(request, response);
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
			Hero hero =new Hero();	
			hero.setId(Integer.valueOf(id));
			hero.setAge(Integer.valueOf(age));
			try {
				hero.setBirthday(sdf.parse(birthday));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hero.setName(name);
			hero.setDescription(description);			
			hero.setSex(sex);
			try {
				HeroDao heroDao=DAOFactory.getDAOInstance();
				boolean flag=heroDao.update(hero);
				heroDao=DAOFactory.getDAOInstance();
				List<Hero> list=heroDao.findAll();
				request.setAttribute("herolist", list);
				request.setAttribute("queryParam", null);
				if(flag){
					request.setAttribute("notifymsg", "修改好汉成功！");
				}
				else{
					request.setAttribute("notifymsg", "修改好汉失败！");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("notifymsg", "修改好汉失败！");
			}	
			
			request.getRequestDispatcher("query.jsp").forward(request, response);
		}
		
	}

}
