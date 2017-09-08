package com.library.manager.controller;

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
import com.library.pojo.BookDesc;
import com.library.service.BookDescService;
import com.library.service.BookService;
@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private BookDescService bookDescService;
	
	
	/**
	 * 
	 * 功能：获取所有书籍信息，不包含作者简介，内容简介，目录等信息
	 * 作者：hwchao
	 * 修改时间：2017年9月8日下午8:29:05
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getBookList(Integer page, Integer rows) {
		EUDataGridResult result = bookService.getBooks(page, rows);
		return result;
	}
	
	/**
	 * 
	 * 功能：保存图书信息，包含作者简介，内容简介，目录等信息
	 * 作者：hwchao
	 * 修改时间：2017年9月8日下午8:30:06
	 */
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult saveBook(Book book,BookDesc bookDesc) throws Exception {
		TaotaoResult result=null;
		result = bookService.insertBook(book);
		bookDesc.setId(book.getId());
		result = bookDescService.insertBookDesc(bookDesc);
		return result;
	}
	/**
	 * 
	 * 功能：删除图书信息，包含作者简介，内容简介，目录等信息
	 * 作者：hwchao
	 * 修改时间：2017年9月8日下午8:30:37
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteBook(String ids){
		try {
			String[] idStrings = ids.split(",");
			for(String id : idStrings){
				Long ID = Long.parseLong(id);
				bookService.deleteBookById(ID);
				bookDescService.deleteBookDesc(ID);
			}
			return TaotaoResult.ok();
		} catch (Exception e) {
			return TaotaoResult.build(500, "删除图书异常");
		}
	}
	/**
	 * 
	 * 功能：更新图书信息，不包含图书作者简介，内容简介，目录等信息
	 * 作者：hwchao
	 * 修改时间：2017年9月8日下午8:31:45
	 */
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
