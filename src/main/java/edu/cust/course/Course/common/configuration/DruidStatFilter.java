package edu.cust.course.Course.common.configuration;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
initParams={
    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),// 忽略资源
    @WebInitParam(name="principalSessionName",value="USER_SESSION"),//配置principalSessionName，使得druid能够知道当前的session的用户是谁
    @WebInitParam(name="profileEnable",value="true")//配置profileEnable能够监控单个url调用的sql列表。
})
/**
 * Druid拦截器，用于查看Druid监控
 */
public class DruidStatFilter extends WebStatFilter {
		
	
}
