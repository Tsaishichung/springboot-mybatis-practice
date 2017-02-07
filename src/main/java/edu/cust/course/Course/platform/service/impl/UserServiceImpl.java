package edu.cust.course.Course.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cust.course.Course.common.mapper.UserMapper;
import edu.cust.course.Course.common.model.User;
import edu.cust.course.Course.platform.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public User login(String account, String password) {
		User user = userMapper.login(account, password);
		return user;
	}
	@Override
	public Integer userRegister(User user) {
		return userMapper.userRegister(user);
	}
	@Override
	public User selectUserById(Integer id) {
		return userMapper.selectUserById(id);
	}
	@Override
	public void updateUserInfo(User user) {
			userMapper.updateUserInfo(user);
	}

}
