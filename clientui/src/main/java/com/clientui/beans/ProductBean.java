package com.clientui.beans;

import lombok.Data;

@Data
public class ProductBean {
	// mêmes attributs que Product
	private int id;
	private String titre;
	private String description;
	private String image;
	private Double prix;
	
	public ProductBean() {
		super();
	}
	
}
