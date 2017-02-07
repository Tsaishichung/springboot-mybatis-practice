package edu.cust.course.Course.common.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author caizc
 * @version 1.0
 * 会议通知公告信息*/
public class Meetting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**公告主键id*/
	private Integer id;
	/**公告标题*/
	private String title;
	/**公告正文内容*/
	private String content;
	/**公告创建时间*/
	private Date createTime;
	/**公告是否过期*/
	private Date meettingTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return  new SimpleDateFormat("yyyy-MM-dd").format(createTime);
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMeettingTime() {
		return new SimpleDateFormat("yyyy-MM-dd").format(meettingTime);
	}
	public void setMeettingTime(Date meettingTime) {
		this.meettingTime = meettingTime;
	}
	
}
