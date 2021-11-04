package com.clientui.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.clientui.beans.ExpeditionBean;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-expeditions")
public interface ExpeditionsProxy {
	/*
	 * on récupère les méthodes liées à l url localhost:9006 (--> /Expeditions/...)
	 */

	@GetMapping(value = "/microservice-expeditions/Expeditions")
	List<ExpeditionBean> findAllExpeditions();

	@GetMapping(value = "/microservice-expeditions/Expeditions/{id}")
	ExpeditionBean findExpeditionById(@PathVariable("id") int id);

	@PostMapping(value = "/microservice-expeditions/Expeditions")
	ResponseEntity<ExpeditionBean> addExpedition(@RequestBody ExpeditionBean expedition);

	@PutMapping(value = "/microservice-expeditions/Expeditions/{id}")
	void updateExpedition(@RequestBody ExpeditionBean expedition, @PathVariable("id") int id);

}
