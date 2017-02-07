package edu.cust.course.Course.platform.service;

import java.util.List;
import java.util.Map;

import edu.cust.course.Course.common.model.Article;


public interface ArticleService {
	/**首页显示全部热门文章列表信息*/	
	public String loadHotArticleList(String limit);
	/**查询单个文章信息，根据文章内容显示文章正文*/
	public Map<String,Object> selectOne(Integer id) throws Exception;
	/**下载次数增加*/
	public void updateDownloadCount(Integer id);
	/**阅读数增加*/
	public void updateViewCount(Integer id);
	/**获取文章类型*/
	public String loadType();
	/**获取文章总数(包含条件查询后的总数)*/
	public Integer countArticle(Map<String,Object> map);
	/**通过limit获取分页后返回的文章列表信息*/
	public List<Article> loadArticleListBySelect(Map<String,Object>map);
	/**根据用户id获取文章列表*/
	public List<Article> selectArticleByUserId(Map<String,Object> map);
	/**根据用户id获取文章数*/
	public Integer selectArticleCountByUserId(Map<String,Object> map);
	/**上传文件，更新数据库*/
	public void upload(Map<String,Object> map);
}
