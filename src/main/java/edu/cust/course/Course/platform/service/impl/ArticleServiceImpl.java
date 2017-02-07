package edu.cust.course.Course.platform.service.impl;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cust.course.Course.common.mapper.ArticleMapper;
import edu.cust.course.Course.common.mapper.ArticleTypeMapper;
import edu.cust.course.Course.common.mapper.UserMapper;
import edu.cust.course.Course.common.model.Article;
import edu.cust.course.Course.common.model.Resource;
import edu.cust.course.Course.platform.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ArticleTypeMapper articleTypeMapper;
	@Override
	public String loadHotArticleList(String limit) {
		return JSONObject.valueToString(articleMapper.loadHotArticleList(limit));
	}
	@Override
	public Map<String,Object> selectOne(Integer id) throws Exception {
		Article article = articleMapper.selectOne(id);
		Map<String,Object> map = new HashMap<String,Object>();
		if(article.getArticle_extend_name().equals("doc")){
			//使用poi获取work text信息
			HWPFDocument docx = new HWPFDocument(new FileInputStream(Resource.getArticle()+article.getArticle_uuid_name()+"."+article.getArticle_extend_name()));
			@SuppressWarnings("resource")
			WordExtractor we = new WordExtractor(docx);
			map.put("context", we.getTextFromPieces());
		}else{
			
			File  file = new File(Resource.getArticle()+article.getArticle_uuid_name()+"."+article.getArticle_extend_name()); 
			if(!file.exists()){
				map.put("context", "该文章已经不存在或被删除!");
			}
			try {
				FileReader fis = new FileReader(file);
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(fis);
				StringBuffer sb = new StringBuffer();
				String str = null;
				while((str=br.readLine())!=null){
					sb.append(str);
				}
				map.put("context", sb);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		map.put("author", userMapper.selectUserById(article.getArticle_user()).getUsername());
		map.put("article", article);
		return map;
	}
	@Override
	public void updateDownloadCount(Integer id) {
		articleMapper.updateDownloadCount(id);
	}
	@Override
	public void updateViewCount(Integer id) {
		articleMapper.updateViewCount(id);
	}
	@Override
	public String loadType() {
		return JSONObject.valueToString(articleTypeMapper.loadType());
	}
	@Override
	public Integer countArticle(Map<String, Object> map) {
		return articleMapper.countArticle(map);
	}
	@Override
	public List<Article> loadArticleListBySelect(Map<String,Object> map) {
		return articleMapper.loadArticleListBySelect(map);
	}
	@Override
	public List<Article> selectArticleByUserId(Map<String,Object> map) {
		return articleMapper.selectArticleByUserId(map);
	}
	@Override
	public Integer selectArticleCountByUserId(Map<String, Object> map) {
		return articleMapper.selectArticleCountByUserId(map);
	}
	@Override
	public void upload(Map<String, Object> map) {
		articleMapper.upload(map);
	}

}
