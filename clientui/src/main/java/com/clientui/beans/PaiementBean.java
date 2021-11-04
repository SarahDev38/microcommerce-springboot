package com.clientui.beans;

import lombok.Data;

@Data
public class PaiementBean {
	// mêmes attributs que Product
	private int id;
    private Integer idCommande;
    private Double montant;
    private Long numeroCarte;
	
	public PaiementBean() {
		super();
	}
	
}
