package edu.cust.course.Course.common.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.cust.course.Course.common.model.User;

@Repository
public interface UserMapper {
    
	//用户登录
	public User login(String account,String password);
	//获取文章作者名称
	public User selectUserById(Integer id);
	//用户注册
	public Integer userRegister(User user);
	//修改用户信息
	public void updateUserInfo(User user);
	//后台用户登录
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