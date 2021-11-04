package com.clientui.web.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.ExpeditionBean;
import com.clientui.beans.PaiementBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.CommandeProxy;
import com.clientui.proxies.ExpeditionsProxy;
import com.clientui.proxies.PaiementProxy;
import com.clientui.proxies.ProductsProxy;

@Controller
public class ClientuiController {
	@Autowired
	private ProductsProxy productsProxy;
	@Autowired
	private CommandeProxy commandesProxy;
	@Autowired
	private PaiementProxy paiementProxy;
	@Autowired
	private ExpeditionsProxy expeditionsProxy;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/")
	public String accueil(Model model) {
		List<ProductBean> produits = productsProxy.findAllProducts();
		model.addAttribute("produits", produits);
		log.info("Récupération de la liste des produits");
		return "Accueil";
	}

	@RequestMapping(value = "/details-produit/{id}")
	public String ficheProduit(@PathVariable("id") int id, Model model) {
		ProductBean produit = productsProxy.findProductById(id);
		model.addAttribute("produit", produit);
		log.info("Récupération des détails du produit avec l'idProduit " + id);
		return "ficheProduit";
	}

	@RequestMapping(value = "/details-produit/commander-produit/{id}")
	public String commandeProduit(@PathVariable("id") int id, Model model) {
		ProductBean produit = productsProxy.findProductById(id);
		model.addAttribute("produit", produit);
		log.info("Récupération des détails du produit commandé avec l'idProduit " + id);
		return "commandeProduit";
	}

	@RequestMapping(value = "/commander-produit/{idProduit}/{montant}")
	public String passerCommande(@PathVariable int idProduit, @PathVariable Double montant, Model model) {
		CommandeBean commande = new CommandeBean();
		commande.setProductId(idProduit);
		commande.setQuantite(1);
		commande.setDateCommande(new Date());
		CommandeBean commandeAjoutee = commandesProxy.addCommande(commande);
		model.addAttribute("commande", commandeAjoutee);
		model.addAttribute("montant", montant);
		log.info("Récupération des détails de la commande avec l'idProduit " + idProduit + ", pour un montant de "
				+ montant);
		return "paiement";
	}

	@RequestMapping(value = "/payer-commande/{idCommande}/{montantCommande}")
	public String payerCommande(@PathVariable int idCommande, @PathVariable Double montantCommande, Model model) {
		PaiementBean paiementAExcecuter = new PaiementBean();
		paiementAExcecuter.setIdCommande(idCommande);
		paiementAExcecuter.setMontant(montantCommande);
		paiementAExcecuter.setNumeroCarte(numcarte()); // on génère un numéro au hasard pour simuler une CB
		ResponseEntity<PaiementBean> paiement = paiementProxy.payACommand(paiementAExcecuter);

		Boolean paiementAccepte = false;
		// si le code est autre que 201 CREATED, c'est que le paiement n'a pas pu
		// aboutir.
		if (paiement.getStatusCode() == HttpStatus.CREATED)
			paiementAccepte = true;
		model.addAttribute("paiementOk", paiementAccepte);
		log.info("Récupération des détails de la commande payée avec l'idCommande " + idCommande
				+ ", pour un montant de " + montantCommande);
		return "confirmation";
	}

	@RequestMapping(value = "/details-expedition/{id}")
	public String ficheExpedition(@PathVariable("id") int id, Model model) {
		ExpeditionBean expedition = expeditionsProxy.findExpeditionById(id);
		model.addAttribute("expedition", expedition);
		log.info("Récupération des détails de l'expédition de la commande avec l'idExpedition" + id);
		return "ficheExpedition";
	}

	// Génère une serie de 16 chiffres au hasard pour simuler vaguement une CB
	private Long numcarte() {
		return ThreadLocalRandom.current().nextLong(1000000000000000L, 9000000000000000L);
	}

}
