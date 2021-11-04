package com.clientui.beans;

import java.util.Date;

import lombok.Data;

@Data
public class CommandeBean {
	// mêmes attributs que Commande
	private int id;
	private Integer productId;
	private Date dateCommande;
	private Integer quantite;
	private Boolean commandePayee;
	
	public CommandeBean() {
		super();
	}
	
}
