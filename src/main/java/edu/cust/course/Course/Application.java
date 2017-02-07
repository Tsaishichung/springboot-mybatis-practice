package edu.cust.course.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.cust.course.Course.common.model.Article;
import edu.cust.course.Course.common.model.Page;
import edu.cust.course.Course.platform.service.ArticleService;
import edu.cust.course.Course.platform.service.UserService;


@MapperScan("edu.cust.course.Course.common.mapper")//扫描com.ai.springboot.MySpringBootProject.mapper下面的mapper接口，其中mapper下面的接口是由mybatis-generator自动生成的
@ServletComponentScan //有此注解后，项目中如果需要使用java原生的servlet和filter，可以在类中使用注解实现，主要是配置Druid监控时需要用到
@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
//@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableScheduling
public class Application {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@RequestMapping("/")
	public ModelAndView index(){
			ModelAndView mav = new ModelAndView();
			Page page = new Page();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("arType", null);
			map.put("article_name", null);
			map.put("show_count", page.getPage_per_show());
			map.put("start_size", (page.getPageNow()-1)*page.getPage_per_show());
			page.setQuery(map);
			List<Article> articleList = articleService.loadArticleListBySelect(page.getQuery());
			for (Article article : articleList) {
				article.setAuthor(userService.selectUserById(article.getArticle_user()).getUsername());
			}
			System.out.println(articleList.size());
			mav.addObject("articleListBySelect", articleList);
			mav.addObject("articleCountBySelect", articleService.countArticle(page.getQuery()));
			double totalCount = articleService.countArticle(page.getQuery());
			double page_per_size = page.getPage_per_show();
			mav.addObject("articlePageCountBySelect", Math.ceil(totalCount/page_per_size));
			mav.addObject("page", page);
			mav.setViewName("index");//访问localhost:8080自动跳转到首页
			return mav;
	}
	private static Logger logger = Logger.getLogger(Application.class);
    public static void main(String[] args) {
    	/**自定义启动函数*/
    	SpringApplication application = new SpringApplication(Application.class);
    	logger.info("application启动！！");
    	application.run(args);
    	//javax.xml.ws.Endpoint.publish("/cxf", new UserServiceImpl());
    }

}
