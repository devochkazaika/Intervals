package com.intervals.exception.service;

import com.intervals.entities.Interval;

public class ServiceExceptionFabric {
    public static IllegalArgumentException illegalTypeException(String type){
        return new IllegalArgumentException("type" + type + "is not supported");
    }
    public static IllegalArgumentException illegalArgumentException(Interval<?> interval){
        return new IllegalArgumentException("illegal " + interval.toString() + "is not supported");
    }

}
