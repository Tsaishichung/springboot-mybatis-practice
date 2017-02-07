package edu.cust.course.Course.common.webservice.service;



import edu.cust.course.Course.common.webservice.dao.User;
public interface UserService {
		public String getName(Long userId);
		public User getUser(Long userId);
}
