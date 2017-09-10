package com.library.restful.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.common.utils.ExceptionUtil;
import com.library.mapper.UserMapper;
import com.library.pojo.User;
import com.library.restful.pojo.ResultType;
import com.library.restful.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired 
	UserMapper userMapper;
	
	/**
	 * 
	 * 功能：保存用户信息
	 * 作者：hwchao
	 * 修改时间：2017年9月10日下午2:54:06
	 * @see com.library.restful.service.UserService#saveUser(com.library.pojo.User)
	 */
	@Override
	public ResultType saveUser(User user) {
		ResultType resultType;
		try{
			userMapper.insert(user);
			resultType = ResultType.ok();
		}catch(Exception exception){
			exception.printStackTrace();
			resultType = ResultType.build(500, ExceptionUtil.getStackTrace(exception));
		}
		return resultType;
	}

}
