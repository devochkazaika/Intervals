package com.intervals.exception.service;

import com.intervals.entities.Interval;
import com.intervals.entities.IntervalRelease.LetterInterval;
import com.intervals.exception.service.types.IllegalTypeException;

public class ServiceExceptionFabric {
    public static IllegalTypeException illegalTypeException(String type){
        return new IllegalTypeException("type" + type + "is not supported");
    }
    public static IllegalArgumentException illegalLetterException(String value){
        return new IllegalArgumentException("illegal letter in " + value);
    }
    public static IllegalArgumentException illegalArgumentException(Interval<?> value){
        return new IllegalArgumentException("illegal interval = " + value.toString());
    }
    public static <T, E> IllegalArgumentException illegalArgumentException(T start, E ended){
        return new IllegalArgumentException("illegal interval = ".concat(
                "{start = " + start.toString() + ", ended = " + ended + "}"));
    }

}
