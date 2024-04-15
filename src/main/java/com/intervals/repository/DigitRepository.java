package com.intervals.repository;

import com.intervals.entities.IntervalRelease.DigitsInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DigitRepository extends JpaRepository<DigitsInterval, Long>{
    @Query("SELECT s FROM DigitsInterval s " +
            "WHERE s.start < ALL(SELECT second.start FROM DigitsInterval second " +
            "WHERE s.id != second.id) " +
            "AND s.ended < ALL(SELECT second.ended FROM DigitsInterval second " +
            "WHERE s.id != second.id)")
    Optional<DigitsInterval> getMinInterval();
}
