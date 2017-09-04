package com.library.restful.service.impl;

import java.util.List;

import javax.management.relation.RoleUnresolved;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.BookCateMapper;
import com.library.pojo.BookCate;
import com.library.pojo.BookCateExample;
import com.library.pojo.BookCateExample.Criteria;
import com.library.restful.pojo.ResultType;
import com.library.restful.service.BookCateService;

@Service
public class BookCateServiceImpl implements BookCateService {

	@Autowired
	BookCateMapper bookCateMapper;
	
	@Override
	public ResultType getCatesByParentId(int id) {
		BookCateExample example = new BookCateExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<BookCate> list = bookCateMapper.selectByExample(example);
		return ResultType.ok(list);
	}

}
