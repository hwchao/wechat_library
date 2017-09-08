package com.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.common.pojo.TaotaoResult;
import com.library.mapper.BookDescMapper;
import com.library.pojo.BookDesc;
import com.library.service.BookDescService;


/**
 * 
 * 功能：图书描述信息服务
 * 作者：hwchao
 * 修改日期：2017年9月8日下午8:11:25
 */
@Service
public class BookDescServiceImpl implements BookDescService {

	@Autowired
	BookDescMapper bookDescMapper;
	
	/**
	 * 
	 * 功能：图书描述信息添加
	 * 作者：hwchao
	 * 修改时间：2017年9月8日下午8:15:43
	 * @see com.library.service.BookDescService#insertBookDesc(com.library.pojo.BookDesc)
	 */
	@Override
	public TaotaoResult insertBookDesc(BookDesc bookDesc) {
		TaotaoResult result=null;
		try{
			bookDescMapper.insert(bookDesc);
			result = TaotaoResult.ok();
		}catch(Exception e){
			result = TaotaoResult.build(500, "信息添加异常");
		}
		return result;
	}
	/**
	 * 
	 * 功能：图书描述信息删除
	 * 作者：hwchao
	 * 修改时间：2017年9月8日下午8:16:03
	 * @see com.library.service.BookDescService#deleteBookDesc(java.lang.Long)
	 */
	@Override
	public TaotaoResult deleteBookDesc(Long id) {
		TaotaoResult result=null;
		try{
			bookDescMapper.deleteByPrimaryKey(id);
			result = TaotaoResult.ok();
		}catch(Exception e){
			result = TaotaoResult.build(500, "信息删除异常");
		}
		return result;
	}

}
