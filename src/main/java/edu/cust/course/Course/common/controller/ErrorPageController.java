package edu.cust.course.Course.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorPageController {
	@RequestMapping("/400")
	public ModelAndView error_400(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("errorPage/400");
		return mav;
	}
	@RequestMapping("/403")
	public ModelAndView error_403(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("errorPage/403");
		return mav;
	}
	@RequestMapping("/404")
	public ModelAndView error_404(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("errorPage/404");
		return mav;
	}
	@RequestMapping("/408")
	public ModelAndView error_408(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("errorPage/408");
		return mav;
	}
	@RequestMapping("/500")
	public ModelAndView error_500(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("errorPage/500");
		return mav;
	}
}
