package edu.cust.course.Course.manage.service;

import java.util.List;
import java.util.Map;

import edu.cust.course.Course.common.model.Article;

public interface ManageArticleService {
	
	public Integer selectArticleCountByManage(Map<String,Object> map);
	
	public List<Article> selectArticleInfoByManage(Map<String,Object> map);
	
	public void updateArticle(Integer id,Integer status);
}
