package edu.cust.course.Course.common.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author caizc
 * @version 1.0
 * 用户信息*/
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**主键id*/
	private Integer id;
	/**账号*/
	private String account;
	/**用户名*/
	private String username;
	/**用户密码*/
	private String password;
	/**用户性别*/
	private Integer sex;
	/**用户电话*/
	private String phone;
	/**用户图片路径*/
	private String userPic;
	/**是否可用*/
	private Integer isUsed;
	/**创建时间*/
	private Date createTime;
	/**是否是vip*/
	private Integer isVip;
	/**用户级别*/
	private Integer isManage;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public Integer getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	public String getCreateTime() {
		return new SimpleDateFormat("yyyy-MM-dd").format(createTime);
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsVip() {
		return isVip;
	}
	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}
	public Integer getIsManage() {
		return isManage;
	}
	public void setIsManage(Integer isManage) {
		this.isManage = isManage;
	}
	
}
