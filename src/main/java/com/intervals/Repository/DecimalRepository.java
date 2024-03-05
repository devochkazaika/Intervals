package com.intervals.Repository;

import com.intervals.Model.Interval;
import com.intervals.Model.IntervalRelease.DecimalInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DecimalRepository extends JpaRepository<DecimalInterval, Long>{
    @Query("SELECT d FROM DecimalInterval d Order by d.start limit 1")
    Interval<Integer> getMinInterval();
}
