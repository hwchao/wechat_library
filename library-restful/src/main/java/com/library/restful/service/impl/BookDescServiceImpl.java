package com.library.restful.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.BookDescMapper;
import com.library.pojo.BookDesc;
import com.library.restful.service.BookDescService;

@Service
public class BookDescServiceImpl implements BookDescService {

	@Autowired
	BookDescMapper bookDescMapper;
	
	@Override
	public BookDesc getBookDescById(long id) {
		BookDesc bookDesc = bookDescMapper.selectByPrimaryKey(id);
		return bookDesc;
	}

}
