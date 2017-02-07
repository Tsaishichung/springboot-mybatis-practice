package edu.cust.course.Course.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.cust.course.Course.common.common.IpUtils;
import edu.cust.course.Course.common.model.Page;
import edu.cust.course.Course.common.model.User;
import edu.cust.course.Course.manage.service.ManageUserService;

@RestController
public class ManageLoginServlet {
	@Autowired
	private ManageUserService userService;
	@RequestMapping("/sys/manage/login")
	public ModelAndView toManagePage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String requestIp = "192.168.86.1";
		String localIp = IpUtils.getLocalIpAddress();
		if(requestIp.equals(localIp)){
			mav.setViewName("/manage/login");
		}else{	
			mav.addObject("fail", "小伙子想干什么，你的IP"+requestIp+"已经被记录！"+localIp);
			mav.setViewName("/manage/403");
		}
		return mav;
	}
	@RequestMapping("/sys/manage/toLogin")
	public ModelAndView doLogin(User user,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String requestIp = "192.168.86.1";
		String localIp = IpUtils.getLocalIpAddress();
		if(requestIp.equals(localIp)){
			if(request.getSession().getAttribute("manageSession")!=null){
				Page page = new Page();
				mav.addObject("flag", "user");
				/**执行用户查询*/
				page.getQuery().put("page_size", page.getPage_per_show());
				page.getQuery().put("start_size", (page.getPageNow()-1)*page.getPage_per_show());
				/**查询总共记录数，计算总页数*/
				double userCount =  userService.manageUserCount();
				double page_size = page.getPage_per_show();
				mav.addObject("userCount", (int)userCount);
				mav.addObject("page_count", Math.ceil(userCount/page_size));
				/**查询当前分页的用户*/
				mav.addObject("userList", userService.manageUserInfo(page.getQuery()));
				mav.addObject("page",page);
				mav.setViewName("/manage/main");
			}else{
			User loginUser = userService.manageLogin(user);
			if(loginUser!=null){
				request.getSession().setAttribute("manageSession", loginUser);
				mav.addObject("flag", "user");
				/**执行用户查询*/
				Page page = new Page();
				page.getQuery().put("page_size", page.getPage_per_show());
				page.getQuery().put("start_size", (page.getPageNow()-1)*page.getPage_per_show());
				/**查询总共记录数，计算总页数*/
				double userCount =  userService.manageUserCount();
				double page_size = page.getPage_per_show();
				mav.addObject("userCount", (int)userCount);
				mav.addObject("page_count", Math.ceil(userCount/page_size));
				/**查询当前分页的用户*/
				mav.addObject("userList", userService.manageUserInfo(page.getQuery()));
				mav.addObject("page",page);
				mav.setViewName("/manage/main");
			}else{
				mav.addObject("manageFail", "您的账号密码错误，请确认您的账号！");
				mav.setViewName("/manage/login");
			}
		}
		}else{	
			mav.addObject("fail", "小伙子想干什么，你的IP"+requestIp+"已经被记录！"+localIp);
			mav.setViewName("/manage/403");
		}
		return mav;
	}
	@RequestMapping("/sys/manage/logout")
	public ModelAndView logout(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/manage/login");
		return mav;
	}
	@RequestMapping("/sys/manage/user/userinfo")
	public ModelAndView manageUser(Page page){
		ModelAndView mav = new ModelAndView();
		page.getQuery().put("page_size", page.getPage_per_show());
		page.getQuery().put("start_size", (page.getPageNow()-1)*page.getPage_per_show());
		/**查询总共记录数，计算总页数*/
		double userCount =  userService.manageUserCount();
		double page_size = page.getPage_per_show();
		mav.addObject("userCount", (int)userCount);
		mav.addObject("page_count", Math.ceil(userCount/page_size));
		/**查询当前分页的用户*/
		mav.addObject("userList", userService.manageUserInfo(page.getQuery()));
		mav.addObject("page",page);
		mav.setViewName("/manage/main");
		mav.addObject("flag", "user");
		return mav;
	}
	@RequestMapping(value="/sys/manage/user/forbid")
	public void toForbidUser(HttpServletRequest request){
		Integer id = Integer.parseInt(request.getParameter("userid"));
		Integer status = userService.selectUserStatus(id);
		if(status==2){
			userService.forbidUser(id, 1);
		}
		if(status==1){
			userService.forbidUser(id, 2);
		}
		
	}
	@RequestMapping(value="/sys/manage/user/resetpwd")
	public void toResetPwdUser(HttpServletRequest request){
		userService.resetPwdUser(Integer.parseInt(request.getParameter("userid")));
	}
}
