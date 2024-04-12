package com.intervals.repository;

import com.intervals.entities.IntervalRelease.LetterInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LetterRepository extends JpaRepository<LetterInterval, Long> {
    @Query("SELECT s FROM LetterInterval s Order by s.start, s.ended limit 1")
    LetterInterval getMinInterval();
}