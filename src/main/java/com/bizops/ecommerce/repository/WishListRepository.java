package com.bizops.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizops.ecommerce.entities.Customer;
import com.bizops.ecommerce.entities.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

	WishList findByCustomer(Customer customer); // Custom method to find by customer
}