package com.library.restful.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.BookCateMapper;
import com.library.pojo.BookCate;
import com.library.pojo.BookCateExample;
import com.library.pojo.BookCateExample.Criteria;
import com.library.restful.pojo.BookCateResultType;
import com.library.restful.pojo.ResultType;
import com.library.restful.service.BookCateService;

@Service
public class BookCateServiceImpl implements BookCateService {

	@Autowired
	BookCateMapper bookCateMapper;
	
	
	/**
	 * 
	 * 功能：查询所有的类别
	 * 作者：hwchao
	 * 修改时间：2017年9月9日下午12:14:16
	 * @see com.library.restful.service.BookCateService#getCatesByParentId(int)
	 */
	@Override
	public ResultType getCatesByParentId(int id) {
		//创建查询条件
		BookCateExample example = new BookCateExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		//查询出所有的子节点
		List<BookCate> list = bookCateMapper.selectByExample(example);
		//遍历节点信息
		Iterator<BookCate> iterator = list.iterator();
		List<BookCateResultType> bookCateResultTypeList = new ArrayList<BookCateResultType>();
		while(iterator.hasNext()){
			BookCate bookCate = iterator.next();
			BookCateResultType bookCateResultType = new BookCateResultType();
			if(bookCate.getIsParent()){
				bookCateResultType.setParent(bookCate);
				BookCateExample bookCateExample = new BookCateExample();
				Criteria criteria2 = bookCateExample.createCriteria();
				criteria2.andParentIdEqualTo(bookCate.getId());
				bookCateResultType.setChildrens(bookCateMapper.selectByExample(bookCateExample));
			}
			bookCateResultTypeList.add(bookCateResultType);
		}
		return ResultType.ok(bookCateResultTypeList); 
	}


//	private List<BookCateResultType> findChildrens(List<BookCate> list) {
//		//创建返回的类别列表
//		List<BookCateResultType> BookCateResultTypeList = new ArrayList<BookCateResultType>();
//		//遍历类别列表
//		Iterator<BookCate> iterator = list.iterator();
//		while(iterator.hasNext()){
//			BookCate bookCate = iterator.next();
//			BookCateResultType bookCateResultType = new BookCateResultType();
//			if(bookCate.getIsParent()){
//				bookCateResultType.setParent(bookCate);
//				bookCateResultType.setChildrens();
//			}
//			if()
//			
//		}
//		return bookCateList;
//	}

}
