package com.library.restful.service;

import com.library.pojo.User;
import com.library.pojo.UserWechat;
import com.library.restful.pojo.ResultType;

public interface UserService {
	public ResultType saveUser(User user);
	public ResultType saveUserWechat(UserWechat userWechat);
}
