package com.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	/**
	 * 
	 * 功能：打开首页
	 * 作者：hwchao
	 * 修改时间：2017年8月22日下午6:08:17
	 */
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	/**
	 * 
	 * 功能：打开其他页面
	 * 作者：hwchao
	 * 修改时间：2017年8月22日下午6:08:33
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
