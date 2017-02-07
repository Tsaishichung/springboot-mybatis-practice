package edu.cust.course.Course.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.cust.course.Course.common.model.Page;
import edu.cust.course.Course.manage.service.ManageArticleService;

@RestController
public class ArticleManageController {
	@Autowired
	private ManageArticleService manageArticleService;
	@RequestMapping("/sys/manage/article/manage")
	public ModelAndView manageArticle(){
		ModelAndView mav = new ModelAndView();
		Page page = new Page();
		/**执行用户查询*/
		page.getQuery().put("status", null);
		page.getQuery().put("page_size", page.getPage_per_show());
		page.getQuery().put("start_size", (page.getPageNow()-1)*page.getPage_per_show());
		/**查询总共记录数，计算总页数*/
		double userCount =  manageArticleService.selectArticleCountByManage(page.getQuery());
		double page_size = page.getPage_per_show();
		mav.addObject("articleCount", (int)userCount);
		mav.addObject("page_count", Math.ceil(userCount/page_size));
		/**查询当前分页的用户*/
		mav.addObject("articleList", manageArticleService.selectArticleInfoByManage(page.getQuery()));
		mav.addObject("page",page);
		mav.addObject("flag", "article");
		mav.setViewName("/manage/main");
		return mav;
	}
	@RequestMapping("/sys/manage/article/manage/do")
	public ModelAndView manageArticleBySelect(Page page){
		ModelAndView mav = new ModelAndView();
		/**执行用户查询*/
		if(page.getQuery().get("status")==null||page.getQuery().get("status").equals("null")){
			page.getQuery().put("status", null);
		}else{
			page.getQuery().put("status", Integer.parseInt((String) page.getQuery().get("status")));
		}
		page.getQuery().put("page_size", page.getPage_per_show());
		page.getQuery().put("start_size", (page.getPageNow()-1)*page.getPage_per_show());
		/**查询总共记录数，计算总页数*/
		double userCount =  manageArticleService.selectArticleCountByManage(page.getQuery());
		double page_size = page.getPage_per_show();
		mav.addObject("articleCount", (int)userCount);
		mav.addObject("page_count", Math.ceil(userCount/page_size));
		/**查询当前分页的用户*/
		mav.addObject("articleList", manageArticleService.selectArticleInfoByManage(page.getQuery()));
		mav.addObject("page",page);
		mav.addObject("flag", "article");
		mav.setViewName("/manage/main");
		return mav;
	}
	@RequestMapping("/sys/manage/article/status")
	public void changeStatus(HttpServletRequest request){
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		manageArticleService.updateArticle(id, status);
	}
	@RequestMapping("/sys/manage/getArticleView")
	public ModelAndView toViewArticle(){
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}
