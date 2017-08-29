package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.mapper.UserMapper;
import com.library.pojo.User;
import com.library.pojo.UserExample;
import com.library.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getUserList() {
		UserExample example = new UserExample();
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

}
