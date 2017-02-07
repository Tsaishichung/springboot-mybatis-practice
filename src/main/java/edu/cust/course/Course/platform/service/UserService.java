package edu.cust.course.Course.platform.service;

import edu.cust.course.Course.common.model.User;

public interface UserService {
	/**用户登录*/
	public User login(String account,String password);
	//用户注册
	public Integer userRegister(User user);
	//根据ID获取用户信息
	public User selectUserById(Integer id);
	//修改用户信息
	public void updateUserInfo(User user);
}
