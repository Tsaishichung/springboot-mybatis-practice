package edu.cust.course.Course.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.cust.course.Course.common.model.Article;

@Repository
public interface ArticleMapper {
	
		public List<Article> loadHotArticleList(String limit);
		
		public Article selectOne(Integer id);
		
		public void updateDownloadCount(Integer id);
		
		public void updateViewCount(Integer id);
		
		public Integer countArticle(Map<String,Object> map);
		
		public List<Article> loadArticleListBySelect(Map<String,Object> map);
		
		public List<Article> selectArticleByUserId(Map<String,Object> map);
		
		public Integer selectArticleCountByUserId(Map<String,Object> map);
		
		public void upload(Map<String,Object> map);
		
		public Integer selectArticleCountByManage(Map<String,Object> map);
		
		public List<Article> selectArticleInfoByManage(Map<String,Object> map);
		
		public void updateArticle(Integer id,Integer status);
}