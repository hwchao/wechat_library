package com.library.mapper;

import java.util.List;

import com.library.dto.ShopcarItem;

public interface ShopcarItemMapper {
	List<ShopcarItem> selectByOpenid(String openid);
}
