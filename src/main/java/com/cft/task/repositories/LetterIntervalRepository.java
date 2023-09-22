package com.cft.task.repositories;

import com.cft.task.entities.LetterInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterIntervalRepository extends JpaRepository<LetterInterval, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM letter_intervals " +
            "ORDER BY lstart, lend " +
            "LIMIT 1")
    LetterInterval findMinimalInterval();
}
