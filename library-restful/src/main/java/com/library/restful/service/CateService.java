package com.library.restful.service;

import java.util.List;

import com.library.pojo.BookCate;

public interface CateService {
	public List<BookCate> getCatesByParentId(int id);
	
}
