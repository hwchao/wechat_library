package com.library.restful.pojo;

import java.util.List;

import com.library.pojo.BookCate;

public class BookCateResultType {
	private BookCate parent;
	private List<BookCate> childrens;
	
	public BookCate getParent() {
		return parent;
	}
	public void setParent(BookCate parent) {
		this.parent = parent;
	}
	public List<BookCate> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<BookCate> childrens) {
		this.childrens = childrens;
	}
	
}
