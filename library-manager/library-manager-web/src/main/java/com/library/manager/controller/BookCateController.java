package com.library.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.common.pojo.EUTreeNode;
import com.library.common.pojo.TaotaoResult;
import com.library.service.BookCateService;


/**
 * 分类管理
 * <p>Title: ContentCategoryController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月8日上午9:23:41
 * @version 1.0
 */
@Controller
@RequestMapping("/content/category")
public class BookCateController {

	@Autowired
	private BookCateService bookCateService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Integer parentId) {
		List<EUTreeNode> list = bookCateService.getCategoryList(parentId);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Integer parentId, String name) {
		TaotaoResult result = bookCateService.insertContentCategory(parentId, name);
		return result;
	}
	
	@RequestMapping("/update")
	public TaotaoResult updateContentCategory(Integer id,String name){
		TaotaoResult result = bookCateService.updateBookCate(id,name);
		return result;
	}
}
