package com.bizops.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizops.ecommerce.entities.Customer;
import com.bizops.ecommerce.entities.WishList;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// Optional: Define custom query methods based on your needs
	WishList findCustomerWishList(Long customerId);

}