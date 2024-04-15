package com.intervals.exception.service;

import com.intervals.entities.Interval;
import com.intervals.entities.IntervalRelease.LetterInterval;

public class ServiceExceptionFabric {
    public static IllegalArgumentException illegalTypeException(String type){
        return new IllegalArgumentException("type" + type + "is not supported");
    }
    public static IllegalArgumentException illegalLetterException(String value){
        return new IllegalArgumentException("illegal letter in " + value);
    }

}
