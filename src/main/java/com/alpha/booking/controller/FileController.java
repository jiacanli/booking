/**   
 * @Package: com.alpha.booking.controller 
 * @author: jiacanli 
 * @date: 2018年5月18日 上午10:49:33 
 */
package com.alpha.booking.controller;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.common.fileAssist.PropertyFileLoader;
import com.alpha.common.web.DataModel;
import com.alpha.common.web.ResultMapUtils;

/** 
 * @ClassName: FileController 
 * @Description: TODO
 * @author: jiacanli
 * @date: 2018年5月18日 上午10:49:33  
 */

@RestController
@RequestMapping("/file")
public class FileController {
	
	
	private static final Properties PATH = PropertyFileLoader.LoadProperties("/path.properties");
	
	/**
	 * 
	 */
	public FileController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/upload")
	public DataModel<Object> upload(
			@RequestParam(value="file",required=true)MultipartFile file,
			@RequestParam(value="category",required=true) String category){
		
		if(category.equals("item")) {
		String path = PATH.getProperty("items");
		String ori_file_name = file.getOriginalFilename();
		File record = new File(path+ori_file_name);
		try {
			file.transferTo(record);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMapUtils.getFailResultMap("400", "上传失败");
		} 
		 return ResultMapUtils.getResultMap("上传成功", "");
		}
		
		return ResultMapUtils.getFailResultMap("400", "参数错误");
	}
	
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(
			@RequestParam(value="category",required=true)String category,
			@RequestParam(value="name",required=true)String name
			){
		try {
		if(category.equals("item")) {
			String pathname = PATH.getProperty("items");
			File file = new File(pathname+name);
			HttpHeaders headers = new HttpHeaders();    
			headers.setContentDispositionFormData("attachment", name);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                    headers, HttpStatus.CREATED);    
		}
		}catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
