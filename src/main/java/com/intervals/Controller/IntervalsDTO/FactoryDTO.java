package com.intervals.Controller.IntervalsDTO;

import com.intervals.Model.IntervalRelease.DigitInterval;
import com.intervals.Model.IntervalRelease.LetterInterval;

import java.util.Arrays;
import java.util.List;

public class FactoryDTO {
    public static List<Integer> createGet(DigitInterval interval){
        if (interval == null){
            return null;
        }
        return Arrays.asList(interval.getStart(), interval.getEnded());
    }
    public static List<String> createGet(LetterInterval interval){
        if (interval == null){
            return null;
        }
        return Arrays.asList(interval.getStart(), interval.getEnded());
    }
}
