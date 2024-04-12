package com.intervals.Repository;

import com.intervals.Model.IntervalRelease.DigitInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DigitRepository extends JpaRepository<DigitInterval, Long>{
    @Query("SELECT d FROM DigitInterval d Order by d.start, d.ended limit 1")
    Optional<DigitInterval> getMinInterval();
}
