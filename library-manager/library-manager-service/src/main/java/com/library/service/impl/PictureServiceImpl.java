package com.library.service.impl;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.common.utils.FtpUtil;
import com.library.common.utils.IDUtils;
import com.library.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@Override
	public Map save(MultipartFile uploadFile){
		Map resultMap = new HashMap<>();
		try{
			String oldName = uploadFile.getOriginalFilename();
			//生成图片文件的路径
			String filePath = new DateTime().toString("/yyyy/MM/dd");
			//生成图片的新名字
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			//使用ftp工具类上产图片
			Boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, 
					filePath, newName, uploadFile.getInputStream());
			if(!result){
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL + filePath + "/" + newName);
			return resultMap;
		}catch(Exception e){
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常");
			return resultMap;
		}
	}

}
