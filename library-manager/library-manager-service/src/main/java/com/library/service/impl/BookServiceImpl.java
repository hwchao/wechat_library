package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.library.common.pojo.EUDataGridResult;
import com.library.common.pojo.TaotaoResult;
import com.library.common.utils.ExceptionUtil;
import com.library.common.utils.IDUtils;
import com.library.mapper.BookMapper;
import com.library.pojo.Book;
import com.library.pojo.BookExample;
import com.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired BookMapper bookMapper;
	@Override
	public EUDataGridResult getBooks(int page, int rows) {
		BookExample bookExample = new BookExample();
		PageHelper.startPage(page,rows);
		List<Book> booklist = bookMapper.selectByExample(bookExample);
		
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(booklist);
		//取记录总条数
		PageInfo<Book> pageInfo = new PageInfo<>(booklist);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	@Override
	public TaotaoResult insertBook(Book book) throws Exception{
		book.setId(IDUtils.genItemId());
		bookMapper.insertSelective(book);
		return TaotaoResult.ok();
	}
	
	@Override
	public void deleteBookById(long id) {
		bookMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TaotaoResult updateBook(Book book) {
		
		try{
			bookMapper.updateByPrimaryKeySelective(book);
			return TaotaoResult.ok();
		}catch (Exception e) {
			TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.build(400, "修改失败");
	}

}
