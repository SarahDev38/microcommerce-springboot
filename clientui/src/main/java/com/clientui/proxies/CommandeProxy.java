package com.clientui.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.clientui.beans.CommandeBean;

//@FeignClient(name = "microservice-commandes", url = "localhost:9002")
@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-commandes")
public interface CommandeProxy {
	/*
	 * on récupère les méthodes liées à l url localhost:9092 (--> /Commandes/...)
	 * pour pouvoir les utiliser à partir de localhost:8080/
	 */

	@PostMapping(value = "/microservice-commandes/Commandes")
	CommandeBean addCommande(@RequestBody CommandeBean commande);

	@GetMapping(value = "/microservice-commandes/Commande/{id}")
	CommandeBean findCommandeById(@PathVariable("id") int id);
	/* préciser la correspondance de la PathVariable {id} */

}
