package com.library.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.restful.pojo.ResultType;
import com.library.restful.service.BookCateService;

@Controller
@RequestMapping("/bookcate")
public class BookCateController {

	@Autowired
	BookCateService bookCateService;
	
	@RequestMapping("/list")
	@ResponseBody
	public ResultType getCateByParentId(@RequestParam(value="id",defaultValue="0") Integer parentId){
		ResultType resultType = bookCateService.getCatesByParentId(parentId);
		return resultType;
	}
}
