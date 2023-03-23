package com.example.JavaAndSpringIncubator.dto;

import com.example.JavaAndSpringIncubator.entities.Stock;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class StockDTO {

    private Long stockReferenceID;
    private String condition;
    private int availableQty;
    private BigDecimal price;
    private String comments;
    private IssuesDTO issue;

    public StockDTO() {

    }

    public Long getStockReferenceID() {
        return stockReferenceID;
    }

    public void setStockReferenceID(Long stockReferenceID) {
        this.stockReferenceID = stockReferenceID;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public IssuesDTO getIssue() {
        return issue;
    }

    public void setIssue(IssuesDTO issue) {
        this.issue = issue;
    }

    public static StockDTO fromEntity(Stock stockEntity) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.stockReferenceID = stockEntity.getStockReferenceID();
        stockDTO.condition = stockEntity.getCondition();
        stockDTO.availableQty = stockEntity.getAvailableQty();
        stockDTO.price = stockEntity.getPrice();
        stockDTO.comments = stockEntity.getComments();
//        stockDTO.issue = IssuesDTO.fromEntity(stockEntity.getIssues());
        return stockDTO;
    }

    @JsonIgnore
    public com.example.JavaAndSpringIncubator.entities.Stock toEntity() {
        com.example.JavaAndSpringIncubator.entities.Stock stock = new com.example.JavaAndSpringIncubator.entities.Stock();
        stock.setStockReferenceID(getStockReferenceID());
        stock.setCondition(getCondition());
        stock.setAvailableQty(getAvailableQty());
        stock.setPrice(getPrice());
        stock.setComments(getComments());
//        stock.setIssues(getIssue().toEntity());
        return stock;
    }
}
