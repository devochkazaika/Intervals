package com.intervals.repository;

import com.intervals.entities.IntervalRelease.LetterInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Репозиторий для работы с интервалами букв.
 */
@Repository
public interface LetterRepository extends JpaRepository<LetterInterval, Long> {

    /**
     * Получает минимальный интервал букв.
     *
     * @return минимальный интервал букв
     */
    @Query("SELECT s FROM LetterInterval s " +
            "WHERE s.start < ALL(SELECT second.start FROM LetterInterval second " +
            "WHERE s.id != second.id) " +
            "AND s.ended < ALL(SELECT second.ended FROM LetterInterval second " +
            "WHERE s.id != second.id)")
    Optional<LetterInterval> getMinInterval();
}