package com.intervals.controller.IntervalsDTO;

import com.intervals.entities.IntervalRelease.DigitsInterval;
import com.intervals.entities.IntervalRelease.LetterInterval;

import java.util.Arrays;
import java.util.List;

public class FactoryDTO {
    public static List<Integer> createGet(DigitsInterval interval){
        return Arrays.asList(interval.getStart(), interval.getEnded());
    }
    public static List<String> createGet(LetterInterval interval){
        return Arrays.asList(interval.getStart(), interval.getEnded());
    }
}
