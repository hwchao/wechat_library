package com.library.restful.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.common.utils.ExceptionUtil;
import com.library.common.utils.JsonUtils;
import com.library.mapper.UserMapper;
import com.library.mapper.UserWechatMapper;
import com.library.pojo.User;
import com.library.pojo.UserWechat;
import com.library.restful.dao.JedisClient;
import com.library.restful.pojo.ResultType;
import com.library.restful.service.UserService;
import com.library.restful.util.WechatUtil;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired 
	UserMapper userMapper;
	@Autowired
	UserWechatMapper userWechatMapper;
	@Autowired
	static JedisClient jedisClient;
	/**
	 * 
	 * 功能：保存用户详细信息
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

	/**
	 * 
	 * 功能：保存用户微信信息
	 * 作者：hwchao
	 * 修改时间：2017年10月15日下午4:43:37
	 * @see com.library.restful.service.UserService#saveUserWechat(com.library.pojo.UserWechat)
	 */
	@Override
	public ResultType saveUserWechat(UserWechat userWechat) {
		ResultType result;
		try{
			if(userWechatMapper.selectByPrimaryKey(userWechat.getOpenid())!=null){
				userWechatMapper.updateByPrimaryKey(userWechat);
			}else {
				userWechatMapper.insert(userWechat);
			}
			result = ResultType.ok();
			String token = WechatUtil.create_nonce_str();
			jedisClient.set(token, JsonUtils.objectToJson(userWechat));
			result.setData(token);
			result.setMsg("success");
		}catch(Exception exception){
			exception.printStackTrace();
			result = ResultType.build(500, ExceptionUtil.getStackTrace(exception));
		}
		return result;
	}

}
