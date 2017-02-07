package edu.cust.course.Course.common.model;

import java.io.Serializable;
/**
 * @author caizc
 * @version 1.0
 * 记录上传等路径资源信息*/
public class Resource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**用户头像路径*/
	private final static String user_pic ="D:\\springbootLog\\course\\user_pic\\";
	/**文章上传路径*/
	private final static String article = "D:\\springbootLog\\course\\springupload\\";
	public static String getUserPic() {
		return user_pic;
	}
	public static String getArticle() {
		return article;
	}
}
