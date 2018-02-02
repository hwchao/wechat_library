package com.library.restful.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.pojo.User;
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
	public ResultType login(String code) {
		Map<String,String> map = WechatUtil.getSessionKeyAndOpenId(code); 
		String openid = map.get("openid");
		User user = new User();
		user.setOpenid(openid);
		ResultType result = userService.saveUser(user);
		return result;
	}
	
}
