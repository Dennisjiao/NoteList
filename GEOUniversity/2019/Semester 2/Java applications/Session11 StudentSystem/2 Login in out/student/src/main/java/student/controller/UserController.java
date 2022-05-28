package student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			return "index";
					
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
	
}
