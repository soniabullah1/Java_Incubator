package com.example.JavaAndSpringIncubator.dto;

import com.example.JavaAndSpringIncubator.entities.Issues;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssuesDTO {

    private Integer issueID;
    private String title;
    private LocalDate publicationDate;
    private String publisher;
    private int seriesNumber;
    private String description;
    private String coverImage;

    public IssuesDTO() {

    }

    public Integer getIssueID() {
        return issueID;
    }

    public void setIssueID(Integer issueID) {
        this.issueID = issueID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public static IssuesDTO fromEntity(com.example.JavaAndSpringIncubator.entities.Issues issuesEntity) {
        IssuesDTO issuesDTO = new IssuesDTO();
        issuesDTO.issueID = issuesEntity.getIssueID();
        issuesDTO.title= issuesEntity.getTitle();
        issuesDTO.publicationDate = issuesEntity.getPublicationDate();
        issuesDTO.publisher = issuesEntity.getPublisher();
        //issuesDTO.seriesNumber = issuesEntity.getSeriesNumber();
        issuesDTO.description = issuesEntity.getDescription();
        issuesDTO.coverImage = issuesEntity.getCoverImage();
        return issuesDTO;
    }

    @JsonIgnore
    public com.example.JavaAndSpringIncubator.entities.Issues toEntity() {
        com.example.JavaAndSpringIncubator.entities.Issues issues = new com.example.JavaAndSpringIncubator.entities.Issues();
        issues.setIssueID(getIssueID());
        issues.setTitle(getTitle());
        issues.setPublicationDate(getPublicationDate());
        issues.setPublisher(getPublisher());
        //issues.setSeriesNumber(getSeriesNumber());
        issues.setDescription(getDescription());
        issues.setCoverImage(getCoverImage());
        return issues;
    }

    public static List<IssuesDTO> toDtos(List<Issues> issuesEntitiy)
    {
        List<IssuesDTO> issuesDtos = new ArrayList<>();
        for(Issues issue : issuesEntitiy)
        {
            issuesDtos.add(IssuesDTO.fromEntity(issue));
        }

        return  issuesDtos;
    }
}
