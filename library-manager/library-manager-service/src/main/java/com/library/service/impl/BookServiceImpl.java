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
import com.library.pojo.BookExample.Criteria;
import com.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired BookMapper bookMapper;
	
	/**
	 * 
	 * 功能：获取所有书籍
	 * 作者：hwchao
	 * 修改时间：2017年8月31日下午3:41:20
	 * @see com.library.service.BookService#getBooks(int, int)
	 */
	@Override
	public EUDataGridResult getBooks(int page, int rows, String id, String title, String author) {
		BookExample bookExample = new BookExample();
		System.out.println(id +  "****" +title + "****"+author);
		Criteria criteria = bookExample.createCriteria();
		if(!"".equals(id)){
			criteria.andIdEqualTo(Long.parseLong(id));
		}
		if(!"".equals(title)){
			criteria.andTitleLike("%"+title+"%");
		}
		if(!"".equals(author)){
			criteria.andAuthorLike("%"+author+"%");
		}
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
	/**
	 * 
	 * 功能：添加书籍
	 * 作者：hwchao
	 * 修改时间：2017年8月31日下午3:41:37
	 * @see com.library.service.BookService#insertBook(com.library.pojo.Book)
	 */
	@Override
	public TaotaoResult insertBook(Book book) throws Exception{
		book.setId(IDUtils.genItemId());
		bookMapper.insertSelective(book);
		return TaotaoResult.ok();
	}
	/**
	 * 
	 * 功能：通过主键删除书籍
	 * 作者：hwchao
	 * 修改时间：2017年8月31日下午3:41:56
	 * @see com.library.service.BookService#deleteBookById(long)
	 */
	@Override
	public void deleteBookById(long id) {
		bookMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 
	 * 功能：通过主键修改书籍信息
	 * 作者：hwchao
	 * 修改时间：2017年8月31日下午3:42:25
	 * @see com.library.service.BookService#updateBook(com.library.pojo.Book)
	 */
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
