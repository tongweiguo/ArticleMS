package com.twg.article.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.twg.article.model.UploadUser;
import com.twg.article.util.UploadHelper;


@Controller
@RequestMapping(value="/upload")
public class UploadController {

	@RequestMapping(value="/testUpload",method = RequestMethod.POST)
	public String testUpload(Model model,HttpServletRequest request,@RequestParam("fileName") MultipartFile fileName){
		String name = request.getParameter("name");
		String desc = request.getParameter("description");
		if(fileName!=null){
			String fName = fileName.getName();
			long fileSize = fileName.getSize();
			String original = fileName.getOriginalFilename();
			System.out.println(fName);
			System.out.println(original);
			System.out.println(fileName);
			System.out.println(fileName.isEmpty());
			System.out.println(fileSize);
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		MultipartFile file = multipartRequest.getFile("fileName");
		
		System.out.println(file.getOriginalFilename());
		System.out.println(name);
		System.out.println(desc);
		model.addAttribute("message", "上传完成.");
		return "null";
	} 
	/**
	 * <上传文件>
	 * 
	 */
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	public String upload(Model model,HttpServletRequest request,@ModelAttribute UploadUser user){
	
		String message = "";
		// 设置格式,图片  
        HttpHeaders headers = new HttpHeaders();  
        headers.setContentType(MediaType.IMAGE_JPEG);
		//获取上传路径
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/music");
		//获取上传文件
		MultipartFile file2 = user.getFile2();
		MultipartFile fileName = user.getFileName();
		File saveFile = new File(path);
		//判断上传路径是否存在
		if(!saveFile.exists()){
			saveFile.mkdir();
		}
		//判断上传文件是否为空
		if(file2.getSize()>0){
			try {
				file2.transferTo(new File(saveFile + "/" + file2.getOriginalFilename()));
				message += "上传成功.";
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message += "上传失败.";
			}
		}
		if(fileName.getSize()>0){
			try {
				fileName.transferTo(new File(saveFile + "/" + fileName.getOriginalFilename()));
				message = "上传成功.";
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "上传失败.";
			}
		}
		//打印上传信息
		System.out.println(user.getName());
		System.out.println(user.getDescription());
		if(user.getFileName()!=null){
			//获取文件名称
			System.out.println(user.getFileName().getOriginalFilename());
			//获取文件字段名称
			user.getFileName().getName();
			//获取文件大小
			user.getFileName().getSize();
			
		}
		if(user.getFile2()!=null){
			System.out.println(user.getFile2().getOriginalFilename());
		}
		model.addAttribute("message",message);
		model.addAttribute("user",user);
		return "Result";
	}
	/**
	 *<展示所有上传文件> 
	 * 
	 */
	@RequestMapping("/listFiles")
	public String getFiles(HttpServletRequest request,Model model){
		//获取文件存放路径
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/music");
		//创建文件所在目录
		File filePath = new File(path);
		if(!filePath.isDirectory()){
			model.addAttribute("message","目录不存在.");
			return "Result";
		}
		//创建存放文件名集合
		Map<String,String> fileMap = new HashMap<String,String>();
		//得到所有文件集合
		UploadHelper.listFile(filePath, fileMap);
		
		model.addAttribute("map", fileMap);
		return "FileList";
	}
	/**
	 * <下载文件>
	 * @throws IOException 
	 * 
	 */
	@RequestMapping("/downLoad")
	public ResponseEntity<byte[]> downLoad(HttpServletRequest request,Model model){
		//获取文件存放路径
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/music");
		//得到文件名称
		String fileName = request.getParameter("fileName");
		//创建文件对象
		File file = new File(path + "/" + fileName);
		//判断文件是否存在
		if(!file.isFile()){
			model.addAttribute("message","文件不存在或已删除.");
			//return "Result";
		}
		//创建响应对象
		HttpHeaders headers = new HttpHeaders();
		//
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//通知浏览器以attachment（下载方式）打开图片
		String downloadFileName = null;
		ResponseEntity<byte[]> respEntity = null;
		try {
			downloadFileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		
			headers.setContentDispositionFormData("attachment", downloadFileName); 
			
		
			respEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			System.out.println("文件不存在或已删除.");
		}
        model.addAttribute("message","文件下载成功.");
		return respEntity; 
	}
}
