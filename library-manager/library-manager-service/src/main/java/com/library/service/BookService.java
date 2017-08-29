package com.library.service;

import com.library.common.pojo.EUDataGridResult;
import com.library.common.pojo.TaotaoResult;
import com.library.pojo.Book;

public interface BookService {
	public EUDataGridResult getBooks(int page, int rows);
	public TaotaoResult insertBook(Book book) throws Exception;
	public void deleteBookById(long id);
	public TaotaoResult updateBook(Book book);
}
