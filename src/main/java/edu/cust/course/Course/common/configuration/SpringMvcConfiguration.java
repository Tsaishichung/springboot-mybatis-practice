package edu.cust.course.Course.common.configuration;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.cust.course.Course.common.common.IpUtils;
import lombok.extern.log4j.Log4j;

@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//添加一个拦截器,拦截前台未登录操作
		registry.addInterceptor(new UserInterceptor()).addPathPatterns("/sys/platform/user/**");//.addPathPatterns()方法设置拦截路径，拦截后台访问路径，放通前台展示信息路径
		//后台拦截器，登录页面与其他页面请求分开，其中登录页面也进行了ip认证操作，其他页面直接判断后台session进行拦截
		registry.addInterceptor(new ManageInterceptor()).addPathPatterns("/sys/manage/manage/**");
	}
	//添加一个资源过滤器
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//第一个参数时访问时的URL，可设为根，第二个参数时静态文件的路径
		registry.addResourceHandler("/**","/resources/**").addResourceLocations("classpath:/static/","classpath:/resources/");
	}
	@Bean
	 public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
	  MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	  ObjectMapper objectMapper = new ObjectMapper();
	  objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	  jsonConverter.setObjectMapper(objectMapper);
	  return jsonConverter;
	 }
	/**添加Controller JSON转换器*/
	 @Bean
	    public HttpMessageConverters customConverters() {
		    //文件下载使用ByteArrayHttpMessageConverter处理
	        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
	        byteArrayHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
	        HttpMessageConverter<?> additional = customJackson2HttpMessageConverter();
	        HttpMessageConverter<?> additional2=byteArrayHttpMessageConverter;
	        return new HttpMessageConverters(additional,additional2);
	    }
	/**配置视图解析器*/
	@Bean
    public InternalResourceViewResolver internalResourceViewResolver () {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");//设置视图解析器，只针对请求转发
        viewResolver.setSuffix(".ftl");
        return viewResolver;
    }
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
	    return new Customizer();//ERROR渲染
	}
	/*//multipart上传配置
	@Bean
	public void multipartResolver(CommonsMultipartResolver commonsMultipartResolver){
		commonsMultipartResolver.setMaxUploadSizePerFile(1073741824);//设置每个文件上传大小不超过100M
		commonsMultipartResolver.setMaxUploadSize(1073741824);
	}*/
	//tomcat自定义设置,可作为properties的补充说明
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
	    factory.setPort(8088);
	    factory.setDisplayName("爸爸的小汤姆");
	    factory.setSessionTimeout(10, TimeUnit.MINUTES);
	    return factory;
	}
	/**
    * 修改DispatcherServlet默认配置（默认dispatcher是实例化XMLApplicationContext）
    * 这里仿照Spring-ws的方式，将项目请求拦截直接交由ContextLoaderListener进行处理，CXF 
    * webservice则按照默认的DispatcherServlet请求拦截进行
    * @param dispatcherServlet
    * @return
    * @author caizc
    * @create  2017年1月17
    */
	@Bean
    public ServletRegistrationBean dispatcherRegistration(ApplicationContext applicationContext) {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		dispatcherServlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(dispatcherServlet, "/");
    }
}


/**
 * @author caizc
 * 错误信息视图渲染*/
class Customizer implements EmbeddedServletContainerCustomizer {
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
		container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
		container.addErrorPages(new ErrorPage(HttpStatus.REQUEST_TIMEOUT, "/408"));
		container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
    }
}
/**
 * @author caizc
 * 前台拦截器设置   */
@Log4j
class UserInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//方法响应前获取参数，在输出对应参数的值
		Iterator<Entry<String, String[]>> ite = request.getParameterMap().entrySet().iterator();
		if(ite.hasNext()){
			String param = ite.next().getKey();
			log.info("参数为："+param+",值为："+request.getParameter(param));
		}
		@SuppressWarnings("rawtypes")
		Map pathVariable = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		log.info("获取URL的参数值为："+pathVariable);
		log.info("当前请求地址为："+request.getRequestURL());
				return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//方法响应后
		/**通过在登录的Controller里验证是否成功登录，建立一个成功的session，则拦截器会在处理请求时生效
		 * session生命周期从建立到web关闭，也可以手工设置session超时时间*/
		//在没登录，获取不到登录session的时候，重定向到登录页面
		if(request.getSession().getAttribute("userSession")==null){
			//
			modelAndView.addObject("handler", "您的登录已经超时，请重新登录！");
			modelAndView.setViewName("/platform/login");
			//response.sendRedirect("index.html");
		}
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//视图渲染时
	}
	
}
/**
 * @author caizc
 * 后台拦截器设置   */
class ManageInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//方法响应前
				return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//方法响应后
		/**通过在登录的Controller里验证是否成功登录，建立一个成功的session，则拦截器会在处理请求时生效
		 * session生命周期从建立到web关闭，也可以手工设置session超时时间*/
		//在没登录，获取不到登录session的时候，重定向到登录页面
		if(request.getSession().getAttribute("manageSession")==null){
			modelAndView.addObject("fail", "小伙子想干什么，你的IP"+IpUtils.getIpAddr(request)+"已经被记录！");
			modelAndView.setViewName("/manage/403");
		}
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//视图渲染时
	}
	
}
