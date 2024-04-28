package com.bizops.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizops.ecommerce.entities.Customer;
import com.bizops.ecommerce.entities.WishList;
import com.bizops.ecommerce.repository.CustomerRepository;
import com.bizops.ecommerce.repository.WishListRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final WishListRepository wishListRepository;
	private final CustomerRepository customerRepository;

	public CustomerController(WishListRepository wishListRepository, CustomerRepository customerRepository) {
		this.wishListRepository = wishListRepository;
		this.customerRepository = customerRepository;
	}

	@GetMapping("/wishlist/{customerId}")
	public WishList getWishlist(@PathVariable Long customerId) {
		// Logic to retrieve wishlist for the customer with customerId
		Customer Customer = this.customerRepository.findById(customerId).get();
		WishList wishList = this.wishListRepository.findByCustomer(Customer);
		// return List.of("Item1", "Item2", "Item3");
		return wishList;
	}

}
