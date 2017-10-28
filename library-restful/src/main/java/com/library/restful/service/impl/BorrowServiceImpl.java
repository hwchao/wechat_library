package com.library.restful.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.common.utils.ExceptionUtil;
import com.library.common.utils.JsonUtils;
import com.library.dto.ShopcarItem;
import com.library.mapper.ShopcarItemMapper;
import com.library.mapper.ShopcarMapper;
import com.library.pojo.Shopcar;
import com.library.pojo.ShopcarExample;
import com.library.pojo.ShopcarExample.Criteria;
import com.library.pojo.UserWechat;
import com.library.restful.dao.JedisClient;
import com.library.restful.pojo.ResultType;
import com.library.restful.service.BorrowService;

@Service
public class BorrowServiceImpl implements BorrowService {

	@Autowired
	JedisClient jedisClient;
	@Autowired
	ShopcarMapper shopcarMapper;
	@Autowired
	ShopcarItemMapper shopcarItemMapper;
	
	@Override
	public ResultType addShopcarItem(String sessionKey, long id) {
		ResultType resultType;
		try {
			UserWechat userWechat = JsonUtils.jsonToPojo(jedisClient.get(sessionKey), UserWechat.class);
			String openid = userWechat.getOpenid();
			ShopcarExample shopcarExample = new ShopcarExample();
			Criteria criteria = shopcarExample.createCriteria();
			criteria.andBidEqualTo(id);
			criteria.andOpenidEqualTo(openid);
			if(shopcarMapper.selectByExample(shopcarExample).size()>0) {
				resultType = ResultType.build(200, "不可重复添加");
			}else {
				Shopcar shopcar = new Shopcar();
				shopcar.setOpenid(openid);
				shopcar.setBid(id);
				shopcar.setOperateTime(new Date());
				shopcarMapper.insert(shopcar);
				resultType = ResultType.build(200, "添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultType = ResultType.build(500, ExceptionUtil.getStackTrace(e));
		}
		return resultType;
	}

	@Override
	public ResultType getShopcarItemsByOpenid(String sessionKey) {
		ResultType result;
		UserWechat userWechat = JsonUtils.jsonToPojo(jedisClient.get(sessionKey), UserWechat.class);
		String openid = userWechat.getOpenid();
		List<ShopcarItem> list = shopcarItemMapper.selectByOpenid(openid);
		result = ResultType.build(200, "获取成功", list);
		return result;
	}

}
