package com.bizops.ecommerce.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.bizops.ecommerce.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	// Optional: Define custom query methods based on your needs
	@Query("SELECT SUM(s.saleAmount) FROM Sale s WHERE s.saleDate = :today")
	BigDecimal findTotalSalesToday(@Param("today") LocalDate today);

	@Query("SELECT s FROM Sale s WHERE s.saleDate BETWEEN :fromDate AND :toDate ORDER BY s.saleAmount DESC")
	List<Sale> findSalesByDateRangeOrderByAmountDesc(@Param("fromDate") LocalDate fromDate,
			@Param("toDate") LocalDate toDate);

	@Query("SELECT p.id, p.name, SUM(s.saleAmount) AS totalSales FROM Sale s JOIN s.product p GROUP BY p.id ORDER BY totalSales DESC")
	List<Object[]> findTopSellingItemsAllTime(@Param("limit") int limit);

	@Query("SELECT p.id, p.name, COUNT(s) AS salesCount FROM Sale s JOIN s.product p"
			+ " WHERE s.saleDate >= :startDate AND s.saleDate <= :endDate" + " GROUP BY p.id ORDER BY salesCount DESC")
	List<Object[]> findTopSellingItemsLastMonth(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
}