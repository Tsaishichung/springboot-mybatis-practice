package edu.cust.course.Course.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cust.course.Course.common.mapper.UserMapper;
import edu.cust.course.Course.common.model.User;
import edu.cust.course.Course.manage.service.ManageUserService;
@Service
public class ManageUserServiceImpl implements ManageUserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public User manageLogin(User user) {
		return userMapper.manageLogin(user);
	}
	@Override
	public Integer manageUserCount() {
		return userMapper.manageUserCount();
	}
	@Override
	public List<User> manageUserInfo(Map<String, Object> map) {
		return userMapper.manageUserInfo(map);
	}
	@Override
	public void forbidUser(Integer id,Integer status) {
		userMapper.forbidUser(id,status);
	}
	@Override
	public void resetPwdUser(Integer id) {
		userMapper.resetPwdUser(id);
	}
	@Override
	public Integer selectUserStatus(Integer id) {
		return userMapper.selectUserStatus(id);
	}

}
