package com.library.restful.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.restful.pojo.ResultType;
import com.library.restful.service.UserService;
import com.library.restful.util.WechatUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 *
	 * 功能：用户登录
	 * 作者：hwchao
	 * 修改时间：2017年10月8日下午8:02:02
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ResultType login(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		WechatUtil.getSessionKeyAndOpenId(code);
		return ResultType.ok();
	}
	
}
