package com.bizops.ecommerce.resp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductSalesResponse {
	private long productId;
	private String productName;
	private double totalSales; // Can be total amount or number of sales

	public ProductSalesResponse(long productId, String productName, double totalSales) {
		this.productId = productId;
		this.productName = productName;
		this.totalSales = totalSales;
	}

	// Getters (omitted for brevity)
}