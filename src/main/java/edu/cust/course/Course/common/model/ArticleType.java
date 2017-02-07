package edu.cust.course.Course.common.model;

import java.io.Serializable;

public class ArticleType implements Serializable{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Integer id;
		private String arType;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getArType() {
			return arType;
		}
		public void setArType(String arType) {
			this.arType = arType;
		}
		
}
