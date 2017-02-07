package edu.cust.course.Course.platform.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.cust.course.Course.common.model.User;
import edu.cust.course.Course.platform.service.UserService;

@RestController
@RequestMapping("/sys/platform")
public class UserLoginController {
	@Autowired
	private UserService userService;
	@RequestMapping("/user/login")
	public ModelAndView userLogin(String account,String password,HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		User user = userService.login(account, password);
		if((user!=null&&user.getIsUsed()==1)||request.getSession().getAttribute("userSession")!=null){
			request.getSession().setAttribute("userSession", user);//platform登录成功创建Session
			try {
				response.sendRedirect("/");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			mav.addObject("message", "登录失败，请检查您的账号密码是否正确");
			mav.setViewName("/platform/login");
		}
		return mav;
	}
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	public ModelAndView toRegister(User user,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		userService.userRegister(user);
		mav.addObject("registerinfo", "注册成功！，请在此处登录！");
		mav.setViewName("/platform/login");
		return mav;
	}
	//注销
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		request.getSession().removeAttribute("userSession");
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mav;
	}
	//跳转注册
	@RequestMapping("/register")
	public ModelAndView registerHtml(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/platform/register");
		return mav;
	}
	//跳转登录
	@RequestMapping("/login")
	public ModelAndView loginHtml(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/platform/login");
		return mav;
	}
	//跳转个人中心
	@RequestMapping("/user/userCenter")
	public ModelAndView toUserCenter(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("flag", "user");
		mav.setViewName("/platform/user/userinfo");
		return mav;
	}
	//修改用户信息
	@RequestMapping("/user/updateUserInfo")
	public ModelAndView toUpdateUserInfo(User user,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		userService.updateUserInfo(user);
		request.getSession().setAttribute("userSession", userService.selectUserById(user.getId()));
		mav.addObject("updateFlag", "修改用户信息成功");
		mav.addObject("flag", "user");
		mav.setViewName("/platform/user/userinfo");
		return mav;
	}
}
