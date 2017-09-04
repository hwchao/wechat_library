package com.library.restful.service;

import com.library.restful.pojo.ResultType;

public interface BookCateService {
	public ResultType getCatesByParentId(int id);
	
}
