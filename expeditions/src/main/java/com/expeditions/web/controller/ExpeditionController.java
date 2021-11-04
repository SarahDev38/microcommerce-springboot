package com.expeditions.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expeditions.dao.ExpeditionDao;
import com.expeditions.model.Expedition;
import com.expeditions.web.exceptions.ExpeditionNotFoundException;
import com.expeditions.web.exceptions.ImpossibleAjouterExpeditionException;

@RestController
public class ExpeditionController {

	@Autowired
	ExpeditionDao expeditionDao;

	@GetMapping(value = "/Expeditions")
	public List<Expedition> findAllExpeditions() {
		List<Expedition> expeditions = expeditionDao.findAll();
		if (expeditions.isEmpty())
			throw new ExpeditionNotFoundException("Aucune expedition n'est créée");
		return expeditions;
	}

	// Récuperer une expedition par son id
	@GetMapping(value = "/Expeditions/{id}")
	public Optional<Expedition> findExpeditionById(@PathVariable int id) {
		Optional<Expedition> expedition = expeditionDao.findById(id);
		if (!expedition.isPresent())
			throw new ExpeditionNotFoundException("L expedition correspondant à l'id " + id + " n'existe pas");
		return expedition;
	}

	// Récuperer une expedition par son id
	@PostMapping(value = "/Expeditions")
	public ResponseEntity<Expedition> addExpedition(@RequestBody Expedition expedition) {
		Expedition nouvelleExpedition = expeditionDao.save(expedition);
		if (nouvelleExpedition == null)
			throw new ImpossibleAjouterExpeditionException("Impossible d'ajouter cette expedition");
		return new ResponseEntity<Expedition>(expedition, HttpStatus.CREATED);
	}

	// Récuperer une expedition par son id
	@PutMapping(value = "/Expeditions/{id}")
	public void updateExpedition(@RequestBody Expedition expedition, @PathVariable int id) {
		if (!expeditionDao.existsById(id))
			throw new ExpeditionNotFoundException("L expedition correspondant à l'id " + id + " n'existe pas");
		expeditionDao.save(expedition);
	}
}
