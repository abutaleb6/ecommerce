package com.bizops.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizops.ecommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	// Optional: Define custom query methods based on your needs
}
