package com.bizops.ecommerce.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sales")  // Optional for table name matching class name
@Data
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne  // Many sales can belong to one customer
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @ManyToOne  // Many sales can be for one product
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @Column(name = "sale_amount", nullable = false)
  private Double saleAmount;

  @Column(name = "sale_date", nullable = false)
  private LocalDate saleDate;

  // Getters and Setters (omitted for brevity)
}
