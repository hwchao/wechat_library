package com.library.restful.service;

import com.library.restful.pojo.ResultType;

public interface BorrowService {
	public ResultType addShopcarItem(String sessionKey,long id);
}
