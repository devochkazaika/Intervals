package com.intervals.Service;

import com.intervals.Model.Interval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntervalseRepository extends JpaRepository<Interval, Long> {
    Optional<Interval> findById(Long id);
}
