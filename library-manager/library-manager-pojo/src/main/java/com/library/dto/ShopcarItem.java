package com.library.dto;

import java.math.BigDecimal;

public class ShopcarItem {
	private Long id;

    private String title;

    private String author;
    
	private String image;

    private BigDecimal price;

    private Integer numRaters;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getNumRaters() {
		return numRaters;
	}

	public void setNumRaters(Integer numRaters) {
		this.numRaters = numRaters;
	}

}
