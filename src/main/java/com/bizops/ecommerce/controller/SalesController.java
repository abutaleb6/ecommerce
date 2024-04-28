package com.bizops.ecommerce.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bizops.ecommerce.entities.Sale;
import com.bizops.ecommerce.repository.SaleRepository;
import com.bizops.ecommerce.resp.ProductSalesResponse;
import com.bizops.ecommerce.resp.SaleByDateResponse;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

	private final SaleRepository saleRepository;

	public SalesController(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}

	// Create an API to return the total sale amount of the current day.
	@GetMapping("/today")
	public ResponseEntity<Double> getTotalSalesToday() {
		LocalDate today = LocalDate.now();
		BigDecimal totalSales = saleRepository.findTotalSalesToday(today);
		return ResponseEntity.ok(totalSales.doubleValue());
	}

	// Create an API to return the max sale day of a certain time range.
	@GetMapping("/max-sales")
	public ResponseEntity<SaleByDateResponse> getMaxSalesByDateRange(@RequestParam(name = "from") LocalDate fromDate,
			@RequestParam(name = "to") LocalDate toDate) {
		List<Sale> sales = saleRepository.findSalesByDateRangeOrderByAmountDesc(fromDate, toDate);
		Optional<Sale> maxSale = sales.stream().findFirst();

		if (maxSale.isPresent()) {
			Sale maxSaleData = maxSale.get();
			SaleByDateResponse response = new SaleByDateResponse(maxSaleData.getSaleDate(),
					maxSaleData.getSaleAmount().doubleValue());
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Create an API to return top 5 selling items of all time (based on total sale
	// amount).
	@GetMapping("/top-selling/all-time")
	public ResponseEntity<List<ProductSalesResponse>> getTopSellingItemsAllTime(
			@RequestParam(name = "limit", defaultValue = "5") int limit) {
		List<Object[]> topSalesData = saleRepository.findTopSellingItemsAllTime(limit);
		List<ProductSalesResponse> topSales = topSalesData.stream()
				.map(data -> new ProductSalesResponse((Long) data[0], (String) data[1], (Double) data[2]))
				.collect(Collectors.toList());
		return ResponseEntity.ok(topSales);
	}

	@GetMapping("/top-selling/last-month")
	public ResponseEntity<List<ProductSalesResponse>> getTopSellingItemsLastMonth(
			@RequestParam(name = "limit", defaultValue = "5") int limit) {
		LocalDate now = LocalDate.now();
		LocalDate firstDayOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
		LocalDate lastDayOfLastMonth = now.minusMonths(1).withDayOfMonth(now.minusMonths(1).lengthOfMonth());

		List<Object[]> topSalesData = saleRepository.findTopSellingItemsLastMonth(firstDayOfLastMonth,
				lastDayOfLastMonth);
		List<ProductSalesResponse> topSales = topSalesData.stream()
				.map(data -> new ProductSalesResponse((Long) data[0], (String) data[1], (Long) data[2]))
				.collect(Collectors.toList());
		return ResponseEntity.ok(topSales);
	}
}
