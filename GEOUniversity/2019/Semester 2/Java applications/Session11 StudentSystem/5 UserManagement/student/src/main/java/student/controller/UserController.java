package student.controller;

import java.sql.SQLException;
import java.util.List;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import student.model.Stuinfo;
import student.model.User;
import student.service.UserService;
import student.util.BaseInterceptor.Interceptor;
import student.util.BaseInterceptor.UserType;

@Controller
@Slf4j
public class UserController {
	
	//private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/*----------------------------打开网站主页---------------------------------------*/	
	@RequestMapping(value={"/","index"},method = RequestMethod.GET)	
	@Interceptor(name = "LoginCheck", usertype=UserType.NOCHECK,failed = "login")
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
	@Interceptor(name = "LoginCheck", usertype=UserType.NOCHECK,failed = "login")
	public String logout(ModelMap model,HttpSession session)
	{
		session.removeAttribute("user");
		session.invalidate();
		User user=new User();
		model.addAttribute("user", user);
		
		return "login";
	}
	
	/*----------------------------打开用户注册页面---------------------------------------*/	
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)	
	public String openRegisterPage(ModelMap model,HttpServletRequest request)
	{
				
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
	/*----------------------------校验学号是否已存在---------------------------------------*/	
	@RequestMapping(value = { "/checkStuid" }, method = RequestMethod.GET)	
	@ResponseBody
	public JSONObject checkStuid(String stuid,String username) throws SQLException
	{
		JSONObject json=new JSONObject();
		boolean isExist=userService.checkStuid(stuid,username);
		json.put("code", isExist?1:0);
		return json;
	}
	
	/*----------------------------保存注册用户---------------------------------------*/
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model,HttpSession session) throws SQLException
	{
		log.warn("=========saveUser is called!");
		//有错误返回登录页面
		if (result.hasErrors()) {
			return "login";
		}		
		userService.save(user);
		session.setAttribute("notify_msg","用户注册成功！");
		
		return "redirect:/login";
	}
	/*----------------------------打开home页面-------------------------------*/	
	@RequestMapping(value={"home"},method = RequestMethod.GET)	
	@Interceptor(name = "LoginCheck", usertype=UserType.NOCHECK,failed = "login")
	public String getHomePage(ModelMap model) throws Exception
	{	
				
		return "home";
	}
	
	/*----------------------------打开用户管理页面-------------------------------*/	
	@RequestMapping(value={"user"},method = RequestMethod.GET)	
	@Interceptor(name = "LoginCheck", usertype=UserType.NOCHECK,failed = "login")
	public String getUserPage(ModelMap model) throws Exception
	{	
				
		return "user/user";
	}
	/*----------------------------执行用户查询-------------------------------*/	
	@RequestMapping(value={"user/getStudentList"},method = RequestMethod.GET,produces = "application/json;charset=UTF-8")	
	@Interceptor(name = "LoginCheck", usertype=UserType.NOCHECK,failed = "login")
	@ResponseBody
	public JSONObject getStudentList(int page,int limit,User user,HttpServletRequest request) throws Exception
	{	
		HttpSession session=request.getSession();
		User sessionuser=(User)session.getAttribute("user");
		int usertype=sessionuser.getUsertype();
		
		JSONObject json=new JSONObject();
		json.put("code", 0);
		json.put("msg", "");		
		JSONArray data = new JSONArray();
		
		if(usertype==UserType.STUDENT){
			User finduser=userService.getById(sessionuser.getUsername());
			Object obj=JSON.toJSON(finduser);
			data.add(obj);
			json.put("count", 1);
			json.put("data", data);
		}
		else if(usertype==UserType.ADMIN){
			long rowcount=userService.getRowCount(user);
			json.put("count", rowcount);
			List<User> list=userService.getList(page, limit, user);
			for(User item:list){
				Object obj=JSON.toJSON(item);
				data.add(obj);
			}
			json.put("data", data);
		}
		return json;
	}
	/*----------------------------打开新增对话框-------------------------------*/	
	@RequestMapping(value={"user/useredit"},method = RequestMethod.GET)	
	@Interceptor(name = "LoginCheck", usertype=UserType.ADMIN,failed = "login")
	public String getNewUserPage(String dialogType,String username,ModelMap model) throws Exception
	{	
		if(dialogType.equals("new")){
			model.addAttribute("dialogType", "new");
			model.addAttribute("title", "新增学生");
			User user=new User();
			user.setStuinfo(new Stuinfo());
			model.addAttribute("user", user);
		}
		else{
			model.addAttribute("dialogType", "edit");
			model.addAttribute("title", "编辑学生");
			User user=userService.getById(username);
			model.addAttribute("user", user);
			
		}
		return "user/useredit";
	}
	/*----------------------------新建/编辑用户---------------------------------------*/
	@RequestMapping(value = { "user/new" }, method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@Interceptor(name = "LoginCheck", usertype=UserType.NOCHECK,failed = "login")
	@ResponseBody
	public JSONObject newEditUser(User user,String dialogType, ModelMap model) throws SQLException
	{
		JSONObject json=new JSONObject();
		if(dialogType.equals("new")){
			userService.save(user);			
		}
		else{
			userService.update(user);			
		}
		json.put("code", 0);
		json.put("msg", "保存用户成功！");
		return json;
	}
	/*----------------------------删除用户---------------------------------------*/
	@RequestMapping(value = { "user/delete" }, method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	@Interceptor(name = "LoginCheck", usertype=UserType.NOCHECK,failed = "login")
	@ResponseBody
	public JSONObject deleteUser(String username,ModelMap model) throws SQLException
	{
		JSONObject json=new JSONObject();
		json.put("code", 0);
		User user=userService.getById(username);
		if(user!=null){
			userService.delete(user);			
			json.put("msg", "删除用户成功！");
		}
		else{			
			json.put("msg", "要删除用户不存在！");
		}		
		return json;
	}
	/*----------------------------批量删除用户---------------------------------------*/
	@RequestMapping(value = { "user/batchdelete" }, method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	@Interceptor(name = "LoginCheck", usertype=UserType.ADMIN,failed = "login")
	@ResponseBody
	public JSONObject batchDeleteUser(String ids,ModelMap model,HttpSession session) throws SQLException
	{
		JSONObject json=new JSONObject();
		
		User sessionuser=(User)session.getAttribute("user");
		int usertype=sessionuser.getUsertype();
		if(usertype==UserType.STUDENT){
			json.put("code", -1);
			json.put("msg", "学生用户不能批量删除！");
			return json;
		}
		
		int ret=userService.deleteByIds(ids);
		if(ret>0){	
			json.put("code", 0);
			json.put("msg", "删除用户成功！");
		}
		else{
			json.put("code", -1);
			json.put("msg", "删除用户失败！");
		}		
		return json;
	}
}
