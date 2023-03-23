package com.example.JavaAndSpringIncubator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Issues")
@Getter
@Setter
public class Issues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issueID")
    private Integer issueID;

    @Column(name = "Title")
    private String title;

    @Column(name = "PublicationDate")
    private LocalDate publicationDate;

    @Column(name = "Publisher")
    private String publisher;

    @Column(name = "SeriesNumber")
    private Integer seriesNumber;

    @Column(name = "Description")
    private String description;

    @Column(name = "CoverImage")
    private String coverImage;

//    @OneToMany
//    @JoinColumn(name = "IssueID", referencedColumnName = "IssueID")
//    private List<Stock> stock;
}
