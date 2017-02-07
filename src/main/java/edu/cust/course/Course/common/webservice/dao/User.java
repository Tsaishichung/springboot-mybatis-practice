package edu.cust.course.Course.common.webservice.dao;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long              userId;
	private String            username;
	private String            email;
	private Date              gmtCreate;
}
