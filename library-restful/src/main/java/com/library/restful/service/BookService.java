package com.library.restful.service;

import java.util.List;

import com.library.pojo.Book;

public interface BookService {
	
	public Book getBookById(long id);
	public List<Book> getBooksByCate(int cateId);
	
}
