package com.example.JavaAndSpringIncubator.repositories;

import com.example.JavaAndSpringIncubator.entities.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuesRepository extends JpaRepository<Issues, Integer> {
//    @Query("SELECT Distinct i.seriesNumber FROM Issues i INNER JOIN Stock s on i.issueID = s.issue.issueID WHERE s.availableQty > 0 and i.title = :title ")
//    List<Integer> findSeriesNumberByTitle(String title);
}
