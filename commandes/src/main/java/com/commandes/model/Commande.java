package com.commandes.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Commande {
	@Id
	@GeneratedValue
	private int id;
	private Integer productId;
	private Date dateCommande;
	private Integer quantite;
	private Boolean commandePayee;

	public Commande() {
	}

	public Commande(int id, Integer productId, Date dateCommande, Integer quantite, Boolean commandePayee) {
		this.id = id;
		this.productId = productId;
		this.dateCommande = dateCommande;
		this.quantite = quantite;
		this.commandePayee = commandePayee;
	}

}
