package com.bizops.ecommerce.resp;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleByDateResponse {
	private LocalDate date;
	private double totalSales;

	public SaleByDateResponse(LocalDate date, double totalSales) {
		this.date = date;
		this.totalSales = totalSales;
	}

	// Getters (omitted for brevity)
}
