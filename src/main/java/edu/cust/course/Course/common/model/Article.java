package edu.cust.course.Course.common.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author caizc
 * @version 1.0
 * 论文文章信息*/
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**文章主键id*/
	private Integer id;
	/**文章名称*/
	private String article_name;
	/**创建时间*/
	private Date create_time;
	/**文章真实名*/
	private String article_uuid_name;
	/**文章扩展名*/
	private String article_extend_name;
	/**是否正常*/
	private Integer is_used;
	/**下载次数*/
	private Integer download_count;
	/**查看次数*/
	private Integer view_count;
	/**文章类型*/
	private String type;
	/**文章作者(用户主键id)*/
	private Integer article_user;
	/**用户的中文名*/
	private String author;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArticle_name() {
		return article_name;
	}
	public void setArticle_name(String article_name) {
		this.article_name = article_name;
	}
	public String getCreate_time() {
		return new SimpleDateFormat("yyyy-MM-dd").format(create_time);
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getArticle_uuid_name() {
		return article_uuid_name;
	}
	public void setArticle_uuid_name(String article_uuid_name) {
		this.article_uuid_name = article_uuid_name;
	}
	public String getArticle_extend_name() {
		return article_extend_name;
	}
	public void setArticle_extend_name(String article_extend_name) {
		this.article_extend_name = article_extend_name;
	}
	public Integer getIs_used() {
		return is_used;
	}
	public void setIs_used(Integer is_used) {
		this.is_used = is_used;
	}
	public Integer getDownload_count() {
		return download_count;
	}
	public void setDownload_count(Integer download_count) {
		this.download_count = download_count;
	}
	public Integer getView_count() {
		return view_count;
	}
	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getArticle_user() {
		return article_user;
	}
	public void setArticle_user(Integer article_user) {
		this.article_user = article_user;
	}
}
