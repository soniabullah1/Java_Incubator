package com.example.JavaAndSpringIncubator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Stock")
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StockReferenceID")
    private Long stockReferenceID;

//    @Column(name = "IssueID")
//    private Long issueID;

    @Column(name = "Condition")
    private String condition;

    @Column(name = "AvailableQty")
    private Integer availableQty;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "IssueID", referencedColumnName = "IssueID")
    private Issues issue;


}
