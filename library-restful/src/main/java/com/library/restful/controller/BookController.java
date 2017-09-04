package com.library.restful.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.pojo.Book;
import com.library.pojo.BookDesc;
import com.library.restful.pojo.ResultType;
import com.library.restful.service.BookService;
import com.library.restful.service.BookDescService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;
	@Autowired
	BookDescService descService;
	
	@RequestMapping("/{id}")
	@ResponseBody
	public ResultType getBookDetail(@PathVariable long id) {
		Book book = bookService.getBookById(id);
		BookDesc bookDesc = descService.getBookDescById(id);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("book", book);
		map.put("desc", bookDesc);
		return ResultType.ok(map);
	}
}
