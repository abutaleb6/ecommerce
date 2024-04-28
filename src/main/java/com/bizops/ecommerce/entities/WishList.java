package com.bizops.ecommerce.entities;

import java.util.Set;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "wishlists")
@Data
public class WishList {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne  // One customer can have one wishlist
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @ManyToMany  // Many products can be in one wishlist (and vice versa)
  @JoinTable(name = "wishlist_products",
      joinColumns = @JoinColumn(name = "wishlist_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private Set<Product> products;

  // Getters and Setters (omitted for brevity)
}
