package com.twg.article.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.twg.article.model.Article;
import com.twg.article.model.ArticleFile;
import com.twg.article.model.User;
import com.twg.article.service.Impl.ArticleServiceImpl;
import com.twg.article.service.Impl.UserServiceImpl;
import com.twg.article.util.PageUtil;
import com.twg.article.util.UploadHelper;


@Controller
@RequestMapping("/article")
public class ArticleController {

	/**
	 * 文章服务层
	 */
	@Autowired
	private ArticleServiceImpl as;
	
	/**
	 * 作者服务层
	 */
	@Autowired
	private UserServiceImpl us;

	/**
	 * <展示文章列表>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/getArticleListByUser")
	public String getArticleListByUserID(HttpServletRequest request, Model model){
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "进入分页查询指定作者的文章");
		List<Article> articleList = null;
		int id;
		String userId = request.getParameter("userId");
		String page = request.getParameter("page");
		int i = 1;
		if(page != null && page != ""){
			i = Integer.parseInt(page);
		}
		if(userId != null && userId != ""){
			//根据作者id查询
			id = Integer.parseInt(userId);
			int count = as.selectArticleCountByUser(id);
			//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "要查询的userID:" + userId);
			PageUtil pu = new PageUtil(i, 3, count);
			pu.setId(Integer.parseInt(userId));
			articleList = as.pageArticleByUser(pu);
			model.addAttribute("articleList", articleList);
			model.addAttribute("userId", id);
			model.addAttribute("page", pu);
			return "showArticleByUser";
		}else{
			//查询所有
			int count = as.selectArticleCount();
			PageUtil pu = new PageUtil(i, 3, count);
			articleList = as.pageArticle(pu);
			model.addAttribute("articleList", articleList);
			model.addAttribute("page", pu);
			return "showArticle";
		}
		
	}
	/**
	 * <展示所有文章列表>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/getArticleList")
	public String getArticleList(HttpServletRequest request, Model model){
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "进入分页查询所有");
		List<Article> articleList = null;
		String page = request.getParameter("page");
		request.getParameter("");
		int ye = 1;
		if(page != null && page != ""){
			ye = Integer.parseInt(page);
		}
		int count = as.selectArticleCount();
		PageUtil pu = new PageUtil(ye, 3, count);
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "分页信息:" + pu);
		articleList = as.pageArticle(pu);
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "文章信息:" + articleList);
		model.addAttribute("articleList", articleList);
		model.addAttribute("page", pu);
		return "showArticle";
	}
	/**
	 * <跳转到添加页面><跳转到添加页面>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAddArticle")
	public String toAddArticle(HttpServletRequest request, Model model){
		String userId = request.getParameter("userId");
		if(userId != null && userId != ""){
			List<User> userList = new ArrayList<User>();
			int id = Integer.parseInt(userId);
			User user = us.selectUserByID(id);
			userList.add(user);
			model.addAttribute("userList", userList);
			return "addArticle";
		}else{
			List<User> userList = us.selectUsers();
			model.addAttribute("userList", userList);
			return "addArticle";
		}
	}
	/**
	 * <添加一条数据到数据库><添加一条数据到数据库>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addArticle")
	public String addArticle(HttpServletRequest request ,@ModelAttribute ArticleFile articleFile, Model model){
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "进入添加方法");
		User user = new User();
		Article article = new Article();
		//String userId =request.getParameter("userId");
		//String title = request.getParameter("title");
		//String content = request.getParameter("content");
		MultipartFile musicFile = articleFile.getFile();
		if(musicFile.getSize()>0){
			String path = request.getSession().getServletContext().getRealPath("/WEB-INF/static/music");
			String musicName = musicFile.getOriginalFilename();
			//处理特殊字符
			musicName = musicName.replace("[", "(");
			musicName = musicName.replace("]", ")");
			musicName = UploadHelper.makeFileName(musicName);
			article.setArticleURL(musicName);
			File file = new File(path);
			if(!file.exists()){
				file.mkdir();
			}
			try {
				musicFile.transferTo(new File(file + "/" + musicName));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "上传失败.");
			}
		}
		
		user.setId(Integer.parseInt(articleFile.getUserId()));
		article.setTitle(articleFile.getTitle());
		article.setContent(articleFile.getContent());
		
		article.setUser(user);
		int i = as.addArticle(article);
		if(i>0){
			model.addAttribute("message", "添加成功");
			return "forward:/article/getArticleList";
		}else{
			model.addAttribute("message", "添加失败,请返回重新操作");
			return "error";
		}
	}
	/**
	 * <跳转修改页面><跳转修改页面>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toUpdateArticle")
	public String toUpdateArticle(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "获取需要的id" + id);
		Article article = as.getArticleListById(Integer.parseInt(id));
		List<User> userList = us.selectUsers();
		model.addAttribute("article", article);
		model.addAttribute("userList", userList);
		
		return "updateArticle";
	}
	/**
	 * <修改一条数据><修改一条数据>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateArticle")
	public String updateArticle(HttpServletRequest request,@ModelAttribute ArticleFile articleFile, Model model){
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "修改数据");
		Article article = new Article();
		User user = new User();
		//String id = request.getParameter("id");
		//String userId = request.getParameter("userId");
		//String title = request.getParameter("title");
		//String content = request.getParameter("content");
		MultipartFile musicFile = articleFile.getFile();
		article.setArticleURL(articleFile.getFileName());
		if(musicFile.getSize() > 0){
			//如果上传文件不为空,就上传并将之前的文件删除
			String path = request.getSession().getServletContext().getRealPath("/WEB-INF/static/music");
			String musicName = musicFile.getOriginalFilename();
			//处理特殊字符
			musicName = musicName.replace("[", "(");
			musicName = musicName.replace("]", ")");
			musicName = UploadHelper.makeFileName(musicName);
			article.setArticleURL(musicName);
			File former = new File(path + "/" +  articleFile.getFileName());
			File file = new File(path);
			if(!file.exists()){
				file.mkdir();
			}
			if(former.isFile()){ 
				boolean b = former.delete();
				System.out.println("删除原文件:" + b);
				//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "删除原文件:" + b);
			}
			try {
				musicFile.transferTo(new File(file + "/" + musicName));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "上传失败.");
			}
		}
		user.setId(Integer.parseInt(articleFile.getUserId()));
		article.setId(Integer.parseInt(articleFile.getId()));
		article.setTitle(articleFile.getTitle());
		article.setContent(articleFile.getContent());
		
		article.setUser(user);
		int i = as.updateArticle(article);
		if(i>0){
			model.addAttribute("message", "操作成功");
			return "forward:/article/getArticleList";
		}else{
			model.addAttribute("message", "修改失败,请返回重新操作");
			return "error";
		}
	}
	/**
	 * <根据id删除一条数据><根据id删除一条数据>
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteArticleByID")
	public String deleteArticleByID(String id, Model model){
		System.out.println("进入删除操作");
		int articleId = Integer.parseInt(id);
		int i = as.deleteArticleById(articleId);
		if(i > 0){
			model.addAttribute("message", "删除成功");
			return "forward:/article/getArticleList";
		}else{
			model.addAttribute("message", "删除失败,");
			return "forward:/article/getArticleList";
		}
	}
	/**
	 * <查询过滤后的列表>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/getFilterArticle")
	public String getFilterArticle(HttpServletRequest request, Model model){
		String newPage = request.getParameter("page");
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "根据" + name + "查询:" + value);
		List<Article> articleList = new ArrayList<Article>();
		User user = new User();
		Article article = new Article();
		int page = 1;
		if(newPage!=null && "" != newPage){
			page = Integer.parseInt(newPage);
		}
		if(value != null && "" != value){
			if("id".equalsIgnoreCase(name)){
				try{
					article.setId(Integer.parseInt(value));
				}catch(Exception e){
					//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "id格式转换错误");
				}
			}else if("title".equalsIgnoreCase(name)){
				article.setTitle(value);
			}else if("content".equalsIgnoreCase(name)){
				article.setContent(value);
			}else if("user".equalsIgnoreCase(name)){
				user.setUserName(value);
				article.setUser(user);
			}
		}
		int count = as.selectFilterCount(article);
		PageUtil pu = new PageUtil(page, 3, count);
		pu.setArticle(article);
		articleList = as.pageFilterArticle(pu);
		model.addAttribute("page", pu);
		model.addAttribute("articleList", articleList);
		model.addAttribute("count", count);
		model.addAttribute("name", name);
		model.addAttribute("value", value);
		return "showFilterArticle";
	}
	/**
	 * <跳转至下载页面>
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toDownload")
	public String toDownload(HttpServletRequest request, Model model){
		//
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/static/music");
		//
		String fileName = request.getParameter("fileName");
		//
		File file = new File(path + "/" + fileName);
		if(!file.isFile()){
			model.addAttribute("message", "文件不存在或已删除!");
			return "DownLoad";
		}
		//
		Map<String,String> fileMap = new HashMap<String,String>();
		fileMap.put(fileName, UploadHelper.parseFileName(fileName));
		model.addAttribute("map", fileMap);
		return "DownLoad";
	}
	/**
	 * <下载文件>
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/downLoad")
	public ResponseEntity<byte[]> downLoadArticle(HttpServletRequest request) throws IOException{
		//获取文件存放路径
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/static/music");
		//获取文件名称
		String fileName = request.getParameter("fileName");
		//创建文件对象
		File file = new File(path + "/" + fileName);
		//判断文件是否存在
		if(!file.isFile()){
			//BaseLogger.writeLog(//BaseLogger.logger, //BaseLogger.INFO, "文件不存在或已删除!");
		}
		//创建响应头
		HttpHeaders headers = new HttpHeaders();
		//控制浏览器下载
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//设置下载文件名称
		headers.setContentDispositionFormData("attachment", UploadHelper.parseFileName(new String(fileName.getBytes("UTF-8"), "ISO-8859-1")));
		//创建响应对象开始下载
		ResponseEntity<byte[]> respEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		return respEntity;
	}
	
}
