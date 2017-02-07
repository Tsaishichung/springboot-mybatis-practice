package edu.cust.course.Course.common.model;

import java.io.Serializable;

/**
 * @author caizc
 * @version 1.0
 * 用户权限菜单*/
public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**主键id*/
	private Integer id;
	/**菜单名称*/
	private String menu_name;
	/**菜单对应访问url*/
	private String url;
	/**不同级别用户对应不同菜单*/
	private Integer user_level;
	/**所属父节点*/
	private Integer parent_node;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getUser_level() {
		return user_level;
	}
	public void setUser_level(Integer user_level) {
		this.user_level = user_level;
	}
	public Integer getParent_node() {
		return parent_node;
	}
	public void setParent_node(Integer parent_node) {
		this.parent_node = parent_node;
	}
	
}
