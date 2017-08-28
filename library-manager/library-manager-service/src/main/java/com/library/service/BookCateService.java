package com.library.service;

import java.util.List;

import com.library.common.pojo.TaotaoResult;

public interface BookCateService {

	List<com.library.common.pojo.EUTreeNode> getCategoryList(Integer parentId);
	TaotaoResult insertContentCategory(Integer parentId, String name);
	TaotaoResult updateBookCate(Integer id,String name);
	public TaotaoResult deleteBookCate(Integer parentId, Integer id);
	
}
