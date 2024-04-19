package com.intervals.repository;

import com.intervals.entities.IntervalRelease.DigitInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с интервалами чисел.
 */
@Repository
public interface DigitRepository extends JpaRepository<DigitInterval, Long> {

    /**
     *
     *
     * @return минимальный интервал чисел
     */
    @Query("SELECT s FROM DigitInterval s " +
            "WHERE s.start < ALL(SELECT second.start FROM DigitInterval second " +
            "WHERE s.id != second.id) " +
            "AND s.ended < ALL(SELECT second.ended FROM DigitInterval second " +
            "WHERE s.id != second.id)")
    Optional<DigitInterval> getMinInterval();
}
