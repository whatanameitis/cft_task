package com.cft.task.repositories;

import com.cft.task.entities.DigitInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitIntervalRepository extends JpaRepository<DigitInterval, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM digit_intervals " +
            "ORDER BY istart, iend " +
            "LIMIT 1")
    DigitInterval findMinimalInterval();
}
