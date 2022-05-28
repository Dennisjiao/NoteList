package student.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import student.model.User;
import student.service.UserService;
import student.util.BaseInterceptor.Interceptor;
import student.util.BaseInterceptor.UserType;
import student.util.Page;
import student.util.SecurityCode;
import student.util.SecurityImage;

@Controller
@RequestMapping("/")
public class userController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MessageSource messageSource;
	/*
	 * 打开首页
	 */
	@RequestMapping(value = { "/", "index" } , method = RequestMethod.GET)
	public String openIndexPage(ModelMap model){
		return "index";
	}
	
	/*
	 * 打开用户注册页面
	 */
	@RequestMapping(value = { "user/new"} , method = RequestMethod.GET)
	public String getRegisterPage(ModelMap model){
		User user=new User();
		model.addAttribute("user",user);
		return "user/new";
	}
	
	@RequestMapping(value = { "user/checkuser"} , method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject checkUser(String username){
		JSONObject json=new JSONObject();
		User user=userService.findById(username);
		if(user!=null){
			json.put("code", -1);
			json.put("msg", "用户名已存在");
			
		}
		else{
			json.put("code", 0);
			json.put("msg", "用户名可用");
		}
		return json;
	}
	
	/*
	 * 保存用户
	 */
	@RequestMapping(value = { "user/new"} , method = RequestMethod.POST)
	public String saveUser(@Valid User user,BindingResult result,ModelMap model)
	{
		if(result.hasErrors()){
			return "user/new";
		}
		int ret=userService.save(user);
		if(ret==-1){
			model.addAttribute("notify_msg", "用户已存在！");
		}
		else if(ret==1){
			model.addAttribute("notify_msg", "用户注册成功！");
		}
		else{
			model.addAttribute("notify_msg", "用户注册失败！");
		}
		return "user/new";
	}
	
	/*
	 * 打开登录页面
	 */
	@RequestMapping(value = { "login"} , method = RequestMethod.GET)
	public String openLoginPage(ModelMap model){
		User user=new User();
		model.addAttribute("user",user);
		return "login";
	}
	
	/*
	 *创建验证码
	 */
	@RequestMapping(value={"createImage"},method=RequestMethod.GET)
	public void createImage(ModelMap model,HttpSession session,HttpServletResponse response) throws IOException
	{
		//获取默认难度和长度的验证码
		String securityCode = SecurityCode.getSecurityCode();		
		//放入session中
		session.setAttribute("SESSION_SECURITY_CODE", securityCode);
		//设置响应的头部
		response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);	
        //生成验证码图片
		BufferedImage image = SecurityImage.createImage(securityCode);
		//写入响应中
		ImageIO.write(image, "jpeg", response.getOutputStream());
		response.getOutputStream().flush();
	}
	/*
	 * 用户登录
	 */
	@RequestMapping(value = { "login"} , method = RequestMethod.POST)
	public String login(@Valid User user,BindingResult result,ModelMap model,HttpServletRequest request)
	{
		if(result.hasErrors()){
			return "login";
		}
		HttpSession session=request.getSession();
		String security_code=(String) session.getAttribute("SESSION_SECURITY_CODE");
		if(!security_code.equals(user.getSecurityCode())){
			FieldError error =new FieldError("user","securityCode",
					messageSource.getMessage("Invalid.user.securityCode",new String[]{user.getSecurityCode()},
							Locale.getDefault()));
			result.addError(error);
			return "login";
		}
		User findUser=userService.login(user.getUsername(), user.getPassword());
		if(findUser!=null){
			session.removeAttribute("SESSION_SECURITY_CODE");
			session.setAttribute("user", findUser);			
			//session.setMaxInactiveInterval(10);	
			return "redirect:/index";
		}
		else{
			model.addAttribute("error", "用户名或密码错误！");
			return "login";
		}		
	}
	/*
	 * 用户登出
	 */
	@RequestMapping(value={"logout"},method = RequestMethod.GET)
	public String logout(HttpSession session,ModelMap model)
	{
		session.removeAttribute("user");
		session.invalidate();
		return "redirect:/index";
	}
	
	/*
	 * 打开编辑删除用户页面
	 */
	@RequestMapping(value={"user/show-{operation}"},method=RequestMethod.GET)
	@Interceptor(name="LoginCheck",usertype=UserType.STUDENT,failed="../login")
	public String showUsers(@PathVariable String operation,ModelMap model,HttpSession session)
	{
		User user=(User)session.getAttribute("user");
		//管理员则返回用户列表
		if(user.getUsertype()==UserType.ADMIN){
			List<User> users=userService.findAll();
			model.addAttribute("users",users);
			return "user/showUsers";
		}
		else{
			//是学生用户，删除操作直接删除自己的账户
			if(operation.equals("delete")){
				return "redirect:/user/delete-"+user.getStuinfo().getStuid()+"-user";
			}
			//是学生用户，直接编辑自己的账户
			else{
				return "redirect:/user/edit-"+user.getStuinfo().getStuid()+"-user";
			}
			
		}		
	}
	/*
	 * 编辑用户
	 */
	@RequestMapping(value={"user/edit-{stuid}-user"},method = RequestMethod.GET)
	@Interceptor(name = "LoginCheck", usertype=UserType.STUDENT,failed = "../login")
	public String openEditPage(@PathVariable String stuid,ModelMap model,HttpSession session)
	{
		User user=(User) session.getAttribute("user");
		if(user.getUsertype()==UserType.STUDENT && !user.getStuinfo().getStuid().equals(stuid))
		{
			model.addAttribute("notify_msg", "你无权编辑别人的信息！");
			return "index";
		}
		user=userService.findByStuId(stuid);
		model.addAttribute("user",user);		
		return "user/edit";
	}
	/*
	 * 删除用户
	 */
	@RequestMapping(value={"user/delete-{stuid}-user"},method = RequestMethod.GET)
	@Interceptor(name = "LoginCheck", usertype=UserType.ADMIN,failed = "../login")
	public String deleteUser(@PathVariable String stuid,ModelMap model,HttpSession session)
	{
		User user=userService.findByStuId(stuid);
		if(userService.delete(user)==1)
			model.addAttribute("notify_msg", "删除用户成功！");
		else
			model.addAttribute("notify_msg", "删除用户失败！");
		user=(User) session.getAttribute("user");
		if(user.getUsertype()==UserType.ADMIN)
		{
			List<User> users=userService.findAll();
			model.addAttribute("users",users);		
			return "user/showUsers";
		}
		else
		{
			return "index";
		}
		
	}
	/*
	 * 更新用户信息
	 */
	@RequestMapping(value={"user/update"},method = RequestMethod.POST)
	@Interceptor(name = "LoginCheck", usertype=UserType.STUDENT,failed = "../login")
	public String updateUser(@Valid User user,BindingResult result,ModelMap model,HttpSession session)
	{
		if (result.hasErrors()) {
			return "user/new";
		}
		if(userService.update(user)==1)
			model.addAttribute("notify_msg", "修改用户信息成功！");
		else
			model.addAttribute("notify_msg", "修改用户信息失败！");
		user=(User) session.getAttribute("user");
		if(user.getUsertype()==UserType.ADMIN)
		{
			List<User> users=userService.findAll();
			model.addAttribute("users",users);		
			return "user/showUsers";
		}
		else
		{
			return "index";
		}
		
	}
	/*
	 * 查询用户信息
	 * 
	 */
	@RequestMapping(value={"user/query"},method=RequestMethod.GET)
	@Interceptor(name = "LoginCheck", usertype=UserType.ADMIN,failed = "../login")
	public String query_get(ModelMap model){
		User user=new User();
		model.addAttribute("user",user);
		Page pageobj=new Page(1,5,0);
		model.addAttribute("pageobj",pageobj);
		return "user/query";
	}
	/*
	 * 执行用户查询
	 */
	@RequestMapping(value={"user/query"},method=RequestMethod.POST)
	@Interceptor(name = "LoginCheck", usertype=UserType.ADMIN,failed = "../login")
	public String query_post(User user,Integer page,Integer limit,ModelMap model){
		if(page==null){
			page=1;
		}
		if(limit==null){
			limit=5;
		}
		List<User> users=userService.query(page, limit, user);		
		Page pageobj=new Page(page,limit,userService.getRowCount(user).intValue());
		model.addAttribute("pageobj",pageobj);
		model.addAttribute("users", users);
		return "user/query";
	}
	
}
