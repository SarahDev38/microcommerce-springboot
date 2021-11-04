package com.produits.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue
	private int id;
	private String titre;
	private String description;
	private String image;
	private Double prix;
	
	public Product() {
	}

	public Product(int id, String titre, String description, String image, Double prix) {
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.image = image;
		this.prix = prix;
	}

}
