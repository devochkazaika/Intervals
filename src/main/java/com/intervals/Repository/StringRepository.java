package com.intervals.Repository;

import com.intervals.Model.Interval;
import com.intervals.Model.IntervalRelease.DecimalInterval;
import com.intervals.Model.IntervalRelease.StringInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StringRepository extends JpaRepository<StringInterval, Long> {
    @Query("SELECT s FROM StringInterval s Order by s.start limit 1")
    Interval<String> getMinInterval();
}