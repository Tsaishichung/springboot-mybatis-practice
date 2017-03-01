package edu.cust.course.Course.platform.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.cust.course.Course.common.model.Article;
import edu.cust.course.Course.common.model.Page;
import edu.cust.course.Course.common.model.Resource;
import edu.cust.course.Course.platform.service.ArticleService;
import edu.cust.course.Course.platform.service.UserService;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sys/platform/")
@Log4j
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@ResponseBody
	@RequestMapping(value="/getHotArticleList",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String getHotArticleList(String limit){
		return articleService.loadHotArticleList(limit);
	}
	@RequestMapping("/download/article/{article_name}/{article_uuid_name}/{article_extend_name}/{article_id}")
	public ResponseEntity<byte[]> fileDownLoad(@PathVariable("article_name") String article_name
			,@PathVariable("article_uuid_name") String article_uuid_name
			,@PathVariable("article_extend_name") String article_extend_name
			,@PathVariable("article_id") Integer article_id
			,HttpServletRequest request
			,HttpServletResponse response) throws IOException{
		if(request.getSession().getAttribute("userSession")==null){
			request.getSession().setAttribute("downloadNotAllow", "下载论文需注册会员，如您已经是会员请点击登录，如不是在此处注册成为注册会员");
			response.sendRedirect("/sys/platform/register");
		}else{
			articleService.updateDownloadCount(article_id);
		}
		System.out.println(article_uuid_name+"=="+article_extend_name);
		HttpHeaders headers = new HttpHeaders();
		//MediaType.APPLICATION_OCTET_STREAM这代表任意的二进制数据传输。
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//设置文件下载显示名称，及编码格式
		headers.setContentDispositionFormData("attachment", article_name+"."+article_extend_name,Charset.forName("UTF-8"));
		File file = new File(System.getProperty("user.dir")+Resource.getArticle()+article_uuid_name+"."+article_extend_name);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),  
                headers, HttpStatus.OK);  //create 201 IE不支持状态码。需要设置为200 OK
	} 
	@RequestMapping(value="/getArticleTypeList",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String getArticleTypeList(){
		return articleService.loadType();
	}
	@RequestMapping("/article/getArticleListBySelect")
	public ModelAndView getArticleListBySelect(Page page){
		ModelAndView mav = new ModelAndView();
		System.out.println(page.getQuery().get("article_name")+"=====");
		if(page.getQuery().get("arType").equals("---请选择---")){
			page.getQuery().put("arType", null);
		}
		page.getQuery().put("show_count", page.getPage_per_show());
		page.getQuery().put("start_size", (page.getPageNow()-1)*page.getPage_per_show());
		List<Article> articleList =articleService.loadArticleListBySelect(page.getQuery());
		for (Article article : articleList) {
			article.setAuthor(userService.selectUserById(article.getArticle_user()).getUsername());
		}
		mav.addObject("articleListBySelect", articleList);
		mav.addObject("articleCountBySelect", articleService.countArticle(page.getQuery()));
		double totalCount = articleService.countArticle(page.getQuery());
		double page_per_size = page.getPage_per_show();
		mav.addObject("articlePageCountBySelect", Math.ceil(totalCount/page_per_size));
		mav.addObject("page", page);
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping("/user/getArticleListByUser/{id}")
	public ModelAndView getArticleListByUser(@PathVariable("id") Integer id){
		ModelAndView mav = new ModelAndView();
		Page page = new Page();
		page.getQuery().put("id", id);
		page.getQuery().put("status", null);
		page.getQuery().put("show_count", page.getPage_per_show());
		page.getQuery().put("start_size", (page.getPageNow()-1)*page.getPage_per_show());
		List<Article> articleList = articleService.selectArticleByUserId(page.getQuery());
		mav.addObject("selectArticleCountByUser", articleService.selectArticleCountByUserId(page.getQuery()));
		mav.addObject("page", page);
		double total_count = articleService.selectArticleCountByUserId(page.getQuery());
		double page_per_size = page.getPage_per_show();
		mav.addObject("articlePageCount", Math.ceil(total_count/page_per_size));
		mav.addObject("articleList", articleList);
		mav.addObject("flag", "article");
		mav.setViewName("/platform/user/userinfo");
		return mav;
	}
	@RequestMapping("/user/getArticleListByUser/select")
	public ModelAndView selectArticleListByUserId(Page page){
		ModelAndView mav = new ModelAndView();
		page.getQuery().put("id", Integer.parseInt((String) page.getQuery().get("id")));
		if(page.getQuery().get("status")==null){
			page.getQuery().put("status", null);
		}else{
			page.getQuery().put("status", Integer.parseInt((String) page.getQuery().get("status")));
		}
		page.getQuery().put("show_count", page.getPage_per_show());
		page.getQuery().put("start_size", (page.getPageNow()-1)*page.getPage_per_show());
		List<Article> articleList = articleService.selectArticleByUserId(page.getQuery());
		System.out.println(articleList.size());
		mav.addObject("selectArticleCountByUser", articleService.selectArticleCountByUserId(page.getQuery()));
		mav.addObject("page", page);
		double total_count = articleService.selectArticleCountByUserId(page.getQuery());
		double page_per_size = page.getPage_per_show();
		mav.addObject("articlePageCount", Math.ceil(total_count/page_per_size));
		mav.addObject("articleList", articleList);
		mav.addObject("flag", "article");
		mav.setViewName("/platform/user/userinfo");
		return mav;
	}
	@RequestMapping("/user/getArticleListByUser/toUploadPage")
	public ModelAndView toUploadPage(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("flag", "upload");
		mav.setViewName("/platform/user/userinfo");
		return mav;
	}
	@RequestMapping("/user/fileUpLoad")
	public ModelAndView toUploadFile(@RequestParam("file") MultipartFile file,Page page){
		ModelAndView mav = new ModelAndView();
		log.info("sb");
		//文件上传操作
		if(!file.isEmpty()){
			try {
				String uuid = UUID.randomUUID().toString();
				String suffix = file.getOriginalFilename().substring(
						file.getOriginalFilename().indexOf(".")+1);
				InputStream is = file.getInputStream();
				page.getQuery().put("id", Integer.parseInt((String)page.getQuery().get("id")));
				page.getQuery().put("article_name", page.getQuery().get("article_name"));//文章名
				page.getQuery().put("article_uuid_name", uuid);
				page.getQuery().put("extend_name", suffix);
				page.getQuery().put("is_used", 3);
				//存入数据的文件名，本地文件也命名为这个
				String filename = System.getProperty("user.dir")+Resource.getArticle()+uuid+"."+suffix;
				File localfile = new File(filename);
				log.error(filename);
				if(!localfile.exists()){
					boolean createFileFlag = localfile.createNewFile();
					if(createFileFlag){
						 OutputStream os=new FileOutputStream(localfile);
					        byte[] buffer=new byte[200];
					        int len;
					        while((len=is.read(buffer))!=-1)
					        {
					            os.write(buffer, 0, len);
					        }
					        os.close();
					        is.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			//执行更新数据库操作
			articleService.upload(page.getQuery());
			//获取数据返回页面操作
			page.getQuery().put("status", null);
			page.getQuery().put("show_count", page.getPage_per_show());
			page.getQuery().put("start_size", (page.getPageNow()-1)*page.getPage_per_show());
			List<Article> articleList = articleService.selectArticleByUserId(page.getQuery());
			mav.addObject("selectArticleCountByUser", articleService.selectArticleCountByUserId(page.getQuery()));
			mav.addObject("page", page);
			double total_count = articleService.selectArticleCountByUserId(page.getQuery());
			double page_per_size = page.getPage_per_show();
			mav.addObject("articlePageCount", Math.ceil(total_count/page_per_size));
			mav.addObject("articleList", articleList);
			mav.addObject("flag", "article");
			mav.setViewName("/platform/user/userinfo");
		}else{
			mav.setViewName("index");
		}
		return mav;
	}
}
