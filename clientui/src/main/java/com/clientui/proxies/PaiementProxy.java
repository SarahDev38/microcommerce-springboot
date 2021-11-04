package com.clientui.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.clientui.beans.PaiementBean;

//@FeignClient(name = "microservice-paiement", url = "localhost:9003")
@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-paiements")
public interface PaiementProxy {
	/*
	 * on récupère les méthodes liées à l url localhost:9092 (--> /Commandes/...)
	 * pour pouvoir les utiliser à partir de localhost:8080/
	 */

	@PostMapping(value = "/microservice-paiements/Paiement")
	ResponseEntity<PaiementBean> payACommand(@RequestBody PaiementBean paiement);

}
