package com.library.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
	public Map save(MultipartFile uploadFile);
}
