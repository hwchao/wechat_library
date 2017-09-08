package com.library.service;

import com.library.common.pojo.TaotaoResult;
import com.library.pojo.BookDesc;

public interface BookDescService {
	public TaotaoResult insertBookDesc(BookDesc bookDesc);
	public TaotaoResult deleteBookDesc(Long id);
}
