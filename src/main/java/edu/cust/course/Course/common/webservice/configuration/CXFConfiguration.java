package edu.cust.course.Course.common.webservice.configuration;


import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import edu.cust.course.Course.common.webservice.service.impl.UserServiceImpl;





@Configuration
@Order
public class CXFConfiguration {
	 	@Bean
	    public ServletRegistrationBean dispatcherServlet() {
	        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
	    }
	    @Bean(name = Bus.DEFAULT_BUS_ID)
	    public SpringBus springBus() {
	        return new SpringBus();
	    }
	    @Bean
	    public Endpoint endpoint(){
	    	//JAX-WS 2.0
	    	return  Endpoint.publish("/cxf", new UserServiceImpl());
	    }
		/*public JaxWsServerFactoryBean jaxWsServerFactoryBean(){
			JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
			jaxWsServerFactoryBean.setAddress("http://localhost:8080/cxf");//服务的地址
			jaxWsServerFactoryBean.setServiceClass(UserService.class);//提供服务的类的类型
			jaxWsServerFactoryBean.setServiceBean(new UserServiceImpl());//提供服务的实例
			jaxWsServerFactoryBean.create();//发布服务  publish()…
			return jaxWsServerFactoryBean;
		}*/
}
