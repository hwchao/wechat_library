package com.library.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.common.pojo.EUDataGridResult;
import com.library.common.pojo.TaotaoResult;
import com.library.pojo.Book;
import com.library.service.BookService;
@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getBookList(Integer page, Integer rows) {
		EUDataGridResult result = bookService.getBooks(page, rows);
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult saveBook(Book book) throws Exception {
		TaotaoResult result = bookService.insertBook(book);
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteBook(String ids){
		try {
			String[] idStrings = ids.split(",");
			for(String id : idStrings){
				bookService.deleteBookById(Long.parseLong(id));
			}
			return TaotaoResult.ok();
		} catch (Exception e) {
			return TaotaoResult.build(500, "删除失败");
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateBook(Book book){
		TaotaoResult result = bookService.updateBook(book);
		System.out.println(book);
		return result;
	}
	
	@InitBinder    
    protected void initBinder(HttpServletRequest request,  
        ServletRequestDataBinder binder) throws Exception {  
        binder.registerCustomEditor(Date.class,   
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
    }  
}
