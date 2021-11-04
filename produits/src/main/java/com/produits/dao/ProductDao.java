package com.produits.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produits.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
}
