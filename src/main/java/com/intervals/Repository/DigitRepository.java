package com.intervals.Repository;

import com.intervals.Model.IntervalRelease.DigitsInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitRepository extends JpaRepository<DigitsInterval, Long>{
    @Query("SELECT d FROM DecimalInterval d Order by d.start, d.ended limit 1")
    DigitsInterval getMinInterval();
}
