package com.twg.article.util;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class UploadHelper {
	
	//文件存储路径
	public static final String FILEPATH = "/WEB-INF/static/upload/";  
	//文件存储路径
	public static final String PATH = "/static/upload/";  

	/**
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @Anthor:twg
	 * @param filename 文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */ 
	public static String makeFileName(String filename){  //2.jpg
		//为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		if(filename == null || "".equals(filename)){
			return null;
		}else{
			return UUID.randomUUID().toString() + "_" + filename;
		}
	}

	/**
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：时间戳+"_"+文件的原始名称
	 * @Anthor:twg
	 * @param filename 文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */ 
	public static String makeFileName2(String filename){  //2.jpg
		//为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		if(filename == null || "".equals(filename)){
			return null;
		}else{
			return new Date().getTime() + "_" + filename;
		}
	}
	/**
	 * <解析文件名称得到原始的文件名称>
	 *
	 *
	 */
	public static String parseFileName(String filename){
		if(filename == null || "".equals(filename)){
			return null;
		}else{
			return filename.substring(filename.indexOf("_") + 1);
		}
	}
	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 * @Method: makePath
	 * @Description: 
	 * @Anthor:twg
	 * @param filename 文件名，要根据文件名生成存储目录
	 * @param savePath 文件存储路径
	 * @return 新的存储目录
	 */ 
	public static String makePath(String filename,String savePath){
		//得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		//构造新的保存目录
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		//File既可以代表文件也可以代表目录
		File file = new File(dir);
		//如果目录不存在
		if(!file.exists()){
			//创建目录
			file.mkdirs();
		}
		return dir;
	}

	/**
	 * @Method: listfile
	 * @Description: 递归遍历指定目录下的所有文件
	 * @Anthor:twg
	 * @param file 即代表一个文件，也代表一个文件目录
	 * @param map 存储文件名的Map集合
	 */ 
	public static void listFile(File file,Map<String,String> map){
		//如果file代表的不是一个文件，而是一个目录
		if(!file.isFile()){
			//列出该目录下的所有文件和目录
			File[] files = file.listFiles();
			//遍历files[]数组
			for (File f : files) {
				//递归
				listFile(f,map);
			}
		}else{
			/**
			   处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
			 file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
			    那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
			 */
			String name = file.getName().substring(file.getName().indexOf("_") + 1);
			map.put(file.getName(),name);
		}
	}
	
	/** 
	 * 字符串转换unicode 
	 */  
	public static String string2Unicode(String string) {  

		StringBuffer unicode = new StringBuffer();  

		for (int i = 0; i < string.length(); i++) {  

			// 取出每一个字符  
			char c = string.charAt(i);  

			// 转换为unicode  
			unicode.append("\\u" + Integer.toHexString(c));  
		}  

		return unicode.toString();  
	}  

	/** 
	 * unicode 转字符串 
	 */  
	public static String unicode2String(String unicode) {  

		StringBuffer string = new StringBuffer();  

		String[] hex = unicode.split("\\\\u");  

		for (int i = 1; i < hex.length; i++) {  

			// 转换出每一个代码点  
			int data = Integer.parseInt(hex[i], 16);  

			// 追加成string  
			string.append((char) data);  
		}  

		return string.toString();  
	}  

}
