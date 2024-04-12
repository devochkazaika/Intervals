package com.intervals.Repository;

import com.intervals.Model.IntervalRelease.LetterInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LetterRepository extends JpaRepository<LetterInterval, Long> {
    @Query("SELECT s FROM LetterInterval s Order by s.start, s.ended limit 1")
    Optional<LetterInterval> getMinInterval();
}