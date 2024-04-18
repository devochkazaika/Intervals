package com.intervals.entities;

public interface MergeAble<T> {
    boolean mergeWith(Interval<T> value);
}
