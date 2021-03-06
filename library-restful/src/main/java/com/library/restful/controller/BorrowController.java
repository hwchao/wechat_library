package com.library.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.restful.pojo.ResultType;
import com.library.restful.service.BorrowService;

@Controller
@RequestMapping("/borrow")
public class BorrowController {

	@Autowired
	BorrowService borrowServie;
	
	/**
	 * 
	 * 功能：添加借阅车记录
	 * 作者：hwchao
	 * 修改时间：2017年10月15日下午10:42:22
	 */
	@RequestMapping(value="/addShopCar",method=RequestMethod.POST)
	@ResponseBody
	public ResultType addShopCar(String sessionKey,String id) {
		ResultType result = borrowServie.addShopcarItem(sessionKey, Long.parseLong(id));
		return result;
	}
	/**
	 * 
	 * 功能：通过用户借阅车的书籍
	 * 作者：hwchao
	 * 修改时间：2017年10月16日下午3:57:50
	 */
	@RequestMapping(value="/get_shopcar_items", method=RequestMethod.POST)
	@ResponseBody
	public ResultType getShopcarItemsByOpenid(String sessionKey) {
		System.out.println(sessionKey);
		ResultType result = borrowServie.getShopcarItemsByOpenid(sessionKey);
		return result;
	}
}
