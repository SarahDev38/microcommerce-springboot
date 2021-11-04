package com.expeditions.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Expedition {
	@Id
	@GeneratedValue
	private int id;
	private int idCommande;
	private int etat;

	public Expedition() {
	}

	public Expedition(int id, int idCommande, int etat) {
		this.id = id;
		this.idCommande = idCommande;
		this.etat = etat;
	}

}
