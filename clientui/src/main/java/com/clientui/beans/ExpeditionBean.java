package com.clientui.beans;

import lombok.Data;

@Data
public class ExpeditionBean {
	// mêmes attributs que Expedition
	private int id;
	private int idCommande;
	private int etat;
	
	public ExpeditionBean() {
		super();
	}
	
}
