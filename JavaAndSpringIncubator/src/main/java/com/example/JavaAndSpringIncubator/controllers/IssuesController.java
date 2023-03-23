package com.example.JavaAndSpringIncubator.controllers;

import com.example.JavaAndSpringIncubator.dto.IssuesDTO;
import com.example.JavaAndSpringIncubator.repositories.IssuesRepository;
import com.example.JavaAndSpringIncubator.repositories.StockRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssuesController {

    @Autowired
    private final IssuesRepository issuesRepository;
    @Autowired
    private final StockRepository stockRepository;

    Logger logger = LogManager.getLogger(IssuesRepository.class.getName());

    public IssuesController(IssuesRepository issuesRepository, StockRepository stockRepository) {
        this.issuesRepository = issuesRepository;
        this.stockRepository = stockRepository;
    }


    @GetMapping
    public ResponseEntity<List<IssuesDTO>> getIssues()
    {
        List<IssuesDTO> issues = IssuesDTO.toDtos(issuesRepository.findAll());
        logger.error("issues: ", issues);
        List<IssuesDTO> limited = issues.subList(80,100);
        return ResponseEntity.ok(limited);
    }
}
