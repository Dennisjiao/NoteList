package student.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import student.model.Stuinfo;
import student.model.User;
import student.service.UserService;
import student.util.BaseInterceptor.Interceptor;
import student.util.BaseInterceptor.UserType;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/*----------------------------打开网站主页---------------------------------------*/	
	@RequestMapping(value={"/","index"},method = RequestMethod.GET)	
	@Interceptor(name = "LoginCheck", usertype=UserType.STUDENT,failed = "login")
	public String getHomePage(ModelMap model,HttpServletRequest request) throws Exception
	{
		return "index";
	}
	/*----------------------------打开登录页面-------------------------------*/	
	@RequestMapping(value={"login"},method = RequestMethod.GET)	
	public String getLoginPage(ModelMap model) throws Exception
	{	
		User user=new User();
		model.addAttribute("user", user);
		
		return "login";
	}
	/*----------------------------用户登录---------------------------------------*/	
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult result, ModelMap model,HttpServletRequest request) throws Exception
	{
		//有错误返回登录页面
		if (result.hasErrors()) {
			return "login";
		}
		
		HttpSession session=request.getSession();		
		User findUser=userService.login(user.getUsername(),user.getPassword());
		
		if(findUser!=null)
		{
			session.setAttribute("user", findUser);
			return "redirect:/index";
					
		}
		else
		{	
			model.addAttribute("notify_msg", "登入失败！请检查用户名、密码是否正确！");
			return "login";
		}		
	}
	
	/*----------------------------用户登出---------------------------------------*/	
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	//@Interceptor(name = "LoginCheck", usertype=UserType.STUDENT,failed = "login")
	public String logout(ModelMap model,HttpSession session)
	{
		session.removeAttribute("user");
		session.invalidate();
		User user=new User();
		model.addAttribute("user", user);
		
		return "login";
	}
	/*----------------------------打开学生注册页面---------------------------------------*/	
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register(ModelMap model)
	{
		User user=new User();
		Stuinfo stuinfo=new Stuinfo();
		user.setStuinfo(stuinfo);
		model.addAttribute("user", user);
		
		return "register";
	}
	/*----------------------------校验用户是否已存在---------------------------------------*/
	//默认情况下，springMVC的@ResponseBody返回的是String类型，如果返回其他类型则会报错。
	@RequestMapping(value = { "/checkuser" }, method = RequestMethod.GET)	
	@ResponseBody
	public JSONObject checkUser(String username) throws SQLException
	{
		JSONObject json=new JSONObject();
		boolean isExist=userService.checkUser(username);
		json.put("code", isExist?1:0);
		return json;
	}
	/*----------------------------保存注册用户---------------------------------------*/
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model,HttpSession session) throws SQLException
	{
		//有错误返回登录页面
		if (result.hasErrors()) {
			return "login";
		}		
		userService.save(user);
		session.setAttribute("notify_msg","用户注册成功！");
		
		return "redirect:/login";
	}
	/*----------------------------校验学号是否已存在---------------------------------------*/	
	@RequestMapping(value = { "/checkStuid" }, method = RequestMethod.GET)	
	@ResponseBody
	public JSONObject checkStuid(String stuid) throws SQLException
	{
		JSONObject json=new JSONObject();
		boolean isExist=userService.checkStuid(stuid);
		json.put("code", isExist?1:0);
		return json;
	}
	/*----------------------------打开home页面-------------------------------*/	
	@RequestMapping(value={"home"},method = RequestMethod.GET)	
	public String getHomePage(ModelMap model) throws Exception
	{	
				
		return "home";
	}
}
