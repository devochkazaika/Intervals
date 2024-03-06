package com.intervals.Controller.IntervalsDTO;

import com.intervals.Model.IntervalRelease.DigitsInterval;
import com.intervals.Model.IntervalRelease.LetterInterval;

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
