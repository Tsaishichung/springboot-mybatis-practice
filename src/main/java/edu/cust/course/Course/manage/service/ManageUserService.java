package edu.cust.course.Course.manage.service;


import java.util.List;
import java.util.Map;

import edu.cust.course.Course.common.model.User;


public interface ManageUserService {
		
	/**后台用户登录*/
	public User manageLogin(User user);
	//后台用户总数获取
	public Integer manageUserCount();
	//后台用户信息获取
	public List<User> manageUserInfo(Map<String,Object> map);
	//禁用用户
	public Integer selectUserStatus(Integer id);
	public void forbidUser(Integer id,Integer status);
	//重置用户密码
	public void resetPwdUser(Integer id);
}
