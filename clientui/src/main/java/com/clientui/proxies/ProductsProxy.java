package com.clientui.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clientui.beans.ProductBean;

//@FeignClient(name = "microservice-produits", url = "localhost:9001")
@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-produits")
public interface ProductsProxy {
	/*
	 * on récupère les méthodes liées à l url localhost:9091 (--> /Produits/...)
	 * pour pouvoir les utiliser à partir de localhost:8080/
	 */

	@GetMapping(value = "/microservice-produits/Produits")
	List<ProductBean> findAllProducts();

	@GetMapping(value = "/microservice-produits/Produits/{id}")
	ProductBean findProductById(@PathVariable("id") int id);
	/* préciser la correspondance de la PathVariable {id} */
}
