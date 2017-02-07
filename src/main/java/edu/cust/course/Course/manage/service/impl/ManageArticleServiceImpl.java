package edu.cust.course.Course.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cust.course.Course.common.mapper.ArticleMapper;
import edu.cust.course.Course.common.model.Article;
import edu.cust.course.Course.manage.service.ManageArticleService;
@Service
public class ManageArticleServiceImpl implements ManageArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	@Override
	public Integer selectArticleCountByManage(Map<String,Object> map) {
		return articleMapper.selectArticleCountByManage(map);
	}

	@Override
	public List<Article> selectArticleInfoByManage(Map<String, Object> map) {
		return articleMapper.selectArticleInfoByManage(map);
	}

	@Override
	public void updateArticle(Integer id, Integer status) {
		articleMapper.updateArticle(id, status);
		
	}

}
