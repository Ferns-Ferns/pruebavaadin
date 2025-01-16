package com.example.application.repositories;
import com.example.application.models.scoreCardModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface scoreCardRepository extends JpaRepository<scoreCardModel, Integer> {

    @Query(value = "SELECT S.NAME AS studentName, S.YEAR AS studentYear, MAX(sc.MARKS_OBTAINED) AS highestScore FROM STUDENT S INNER JOIN SCORE_CARD SC ON S.ROLL_NUMBER = SC.ROLL_NUMBER GROUP BY S.NAME, S.YEAR", nativeQuery = true)
    List<Map<String, Object>> findHighsScoreByStudent();
}
