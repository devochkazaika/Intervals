package com.intervals.controller.IntervalsDTO;

import com.intervals.entities.IntervalRelease.DigitInterval;
import com.intervals.entities.IntervalRelease.LetterInterval;

import java.util.Arrays;
import java.util.List;

/**
 * Фабрика для создания DTO.
 */
public class FactoryDTO {

    /**
     * Создает список значений для числового интервала.
     *
     * @param interval числовой интервал
     * @return список значений начала и конца интервала
     */
    public static List<Long> createGet(DigitInterval interval){
        if (interval == null) return null;
        return Arrays.asList(interval.getStart(), interval.getEnded());
    }

    /**
     * Создает список значений для интервала строк.
     *
     * @param interval интервал строк
     * @return список значений начала и конца интервала
     */
    public static List<String> createGet(LetterInterval interval){
        if (interval == null) return null;
        return Arrays.asList(interval.getStart(), interval.getEnded());
    }
}
