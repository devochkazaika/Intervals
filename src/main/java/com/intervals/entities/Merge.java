package com.intervals.entities;

public interface Merge<T> {
    boolean mergeWith(Interval<T> value);
}
