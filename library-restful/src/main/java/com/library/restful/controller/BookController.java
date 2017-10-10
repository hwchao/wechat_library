package com.library.restful.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.pojo.Book;
import com.library.pojo.BookDesc;
import com.library.restful.dao.JedisClient;
import com.library.restful.pojo.ResultType;
import com.library.restful.service.BookDescService;
import com.library.restful.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;
	@Autowired
	BookDescService bookDescService;
	
	/**
	 * 
	 * 功能：获取图书的所有信息
	 * 作者：hwchao
	 * 修改时间：2017年9月12日下午2:27:51
	 */
	@RequestMapping("/{id}")
	@ResponseBody
	public ResultType getBookDetail(@PathVariable long id) {
		Book book = bookService.getBookById(id);
		BookDesc bookDesc = bookDescService.getBookDescById(id);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("book", book);
		map.put("desc", bookDesc);
		return ResultType.ok(map);
	}
	
	/**
	 * 
	 * 功能：获取书籍的目录信息
	 * 作者：hwchao
	 * 修改时间：2017年9月14日上午11:21:17
	 */
	@RequestMapping("/bookcatalog/{id}")
	@ResponseBody
	public ResultType getBookCatalog(@PathVariable long id){
		ResultType resultType = bookService.getgetBookCatelogById(id);
		return resultType;
	}
	
	/**
	 * 
	 * 功能：根据分类Id获取该类别书籍
	 * 作者：hwchao
	 * 修改时间：2017年9月12日下午2:28:21
	 */
	@RequestMapping("/cate/{id}")
	@ResponseBody
	public ResultType getBooksByCateId(@PathVariable int id, int page, int rows){
		ResultType result = bookService.getBooksByCate(id, page, rows);
		return result;
	}
	
	/**
	 * 
	 * 功能：根据搜索关键字获取书籍信息
	 * 作者：hwchao
	 * 修改时间：2017年9月12日下午2:32:23
	 */
	@RequestMapping(value="/search/query")
	@ResponseBody
	public ResultType getBooksBySearchKey(String key, int page, int rows){
		try {
			key = new String(key.getBytes("ISO8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		ResultType resultType = bookService.searchByKey(key, page, rows);
		return resultType;
	}
	
}
