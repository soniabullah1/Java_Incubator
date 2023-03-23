package com.example.JavaAndSpringIncubator.repositories;

import com.example.JavaAndSpringIncubator.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query("SELECT s FROM Stock s WHERE s.issue.issueID = ?1 AND s.availableQty > 0")
    List<Stock> findStockByIssueID(Integer issueID);

}