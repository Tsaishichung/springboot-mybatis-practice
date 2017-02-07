package edu.cust.course.Course.common.controller;



import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.cust.course.Course.common.model.Meetting;
import edu.cust.course.Course.platform.service.ArticleService;
import edu.cust.course.Course.platform.service.MeettingService;

@Controller
public class CommonController {
		@Autowired
	    private ArticleService articleService;
		@Autowired
	    private MeettingService meettingService;
		@RequestMapping("/tocust")
		public void gotoCust(HttpServletResponse response){
			try {
				response.sendRedirect("http://www.cust.edu.cn");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@ResponseBody
		@RequestMapping("/sys/platform/viewing/{viewType}/{id}")
		public ModelAndView toShowInfoInContextRight(@PathVariable("viewType")String viewType,@PathVariable("id")Integer id){
			ModelAndView mav = new ModelAndView();
			if(viewType.equals("meetting")){
				Meetting meetting = meettingService.selectOne(id);
				mav.addObject("meettinginfo", meetting);
			}else if(viewType.equals("article")){
				try {
					Map<String,Object> map = articleService.selectOne(id);
					articleService.updateViewCount(id);
					mav.addObject("articleinfo", map);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mav.setViewName("/platform/show");
			return mav;
			
		}
}
