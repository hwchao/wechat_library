package com.library.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.library.mapper.BookMapper;
import com.library.pojo.Book;
import com.library.pojo.BookExample;
import com.library.pojo.BookExample.Criteria;
import com.library.restful.pojo.ResultType;
import com.library.restful.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired 
	BookMapper bookMapper;
	
	/**
	 * 
	 * 功能：通过主键获得书籍的基本信息
	 * 作者：hwchao
	 * 修改时间：2017年9月3日下午4:59:11
	 * @see com.library.restful.service.BookService#getBookById(long)
	 */
	@Override
	public Book getBookById(long id) {
		Book book = bookMapper.selectByPrimaryKey(id);
		return book;
	}
	/**
	 * 
	 * 功能：通过分类id获得书籍列表,
	 * 		传入参数 CateId 分类的Id, page 起始页， rows 每页的大小
	 * 作者：hwchao
	 * 修改时间：2017年9月3日下午4:59:40
	 * @see com.library.restful.service.BookService#getBooksByCate(int)
	 */
	@Override
	public ResultType getBooksByCate(int cateId, int page, int rows) {
		BookExample example = new BookExample();
		Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(cateId);
		PageHelper.startPage(page, rows);
		List<Book> books = bookMapper.selectByExample(example);
		return ResultType.ok(books);
	}
	
	/**
	 * 
	 * 功能：通过关键字来搜索图书信息
	 * 作者：hwchao
	 * 修改时间：2017年9月12日下午2:03:03
	 * @see com.library.restful.service.BookService#searchByKey(java.lang.String)
	 */
	@Override
	public ResultType searchByKey(String key,int page, int rows) {
		//创建查询条件
		BookExample example = new BookExample();
        Criteria criteria = example.or();
		Criteria criteria2 = example.or();
		Criteria criteria3 = example.or();
		Criteria criteria4 = example.or();
		criteria.andTitleLike('%'+key+'%');
		criteria2.andSubtitleLike('%'+key+'%');
		criteria3.andIsbn13EqualTo(key);
		criteria4.andIsbn10EqualTo(key);
		//设置分页查找
		PageHelper.startPage(page, rows);
		List<Book> list= bookMapper.selectByExample(example);
		return ResultType.ok(list);
	}

}
