package com.produits.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.produits.configurations.ApplicationPropertiesConfiguration;
import com.produits.dao.ProductDao;
import com.produits.model.Product;
import com.produits.web.exceptions.ProductNotFoundException;

@RestController
public class ProductController implements HealthIndicator {

	@Autowired
	ProductDao productDao;

	@Autowired
	ApplicationPropertiesConfiguration appProperties;

	@GetMapping(value = "/Produits")
	public List<Product> findAllProducts() {
		List<Product> products = productDao.findAll();
		if (products.isEmpty())
			throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");
		List<Product> listeLimitee = products.subList(0, appProperties.getLimiteDeProduits());
		return listeLimitee;
	}

	// Récuperer un produit par son id
	@GetMapping(value = "/Produits/{id}")
	public Optional<Product> findProductById(@PathVariable int id) {
		Optional<Product> product = productDao.findById(id);
		if (!product.isPresent())
			throw new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");
		return product;
	}

	@Override
	public Health health() {
		List<Product> products = productDao.findAll();
		if (products.isEmpty())
			return Health.down().build();
		return Health.up().build();
	}
}
