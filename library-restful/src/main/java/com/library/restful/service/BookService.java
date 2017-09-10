package com.library.restful.service;

import com.library.pojo.Book;
import com.library.restful.pojo.ResultType;

public interface BookService {
	
	public Book getBookById(long id);
	public ResultType getBooksByCate(int cateId, int page, int rows);
}
