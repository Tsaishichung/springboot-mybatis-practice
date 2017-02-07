package edu.cust.course.Course.common.webservice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import edu.cust.course.Course.common.webservice.dao.User;
import edu.cust.course.Course.common.webservice.service.UserService;

@WebService(targetNamespace="http://service.webservice.common.Course.course.cust.edu/")
public class UserServiceImpl implements UserService {
	private Map<Long, User> userMap = new HashMap<Long, User>();
	public UserServiceImpl(){
	User user = new User();
	 user.setUserId(10001L);
     user.setUsername("liyd1");
     user.setEmail("liyd1@qq.com");
     user.setGmtCreate(new Date());
     userMap.put(user.getUserId(), user);
     user = new User();
     user.setUserId(10002L);
     user.setUsername("liyd2");
     user.setEmail("liyd2@qq.com");
     user.setGmtCreate(new Date());
     userMap.put(user.getUserId(), user);
     user = new User();
     user.setUserId(10003L);
     user.setUsername("liyd3");
     user.setEmail("liyd3@qq.com");
     user.setGmtCreate(new Date());
     userMap.put(user.getUserId(), user);
}	
	@WebMethod
	@Override
	public String getName(@WebParam(name = "userId")Long userId) {
		 return "liyd-" + userId;
	}
	@WebMethod
	@Override
	public User getUser(@WebParam(name = "userId")Long userId) {
		return userMap.get(userId);
	}

}