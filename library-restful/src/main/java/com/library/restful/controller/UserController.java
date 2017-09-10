package com.library.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.library.restful.pojo.ResultType;
import com.library.restful.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	public ResultType insertUser(){
		return ResultType.ok();
	}
}
