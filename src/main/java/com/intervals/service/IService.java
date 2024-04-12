package com.intervals.service;


import com.intervals.entities.Interval;

import java.util.ArrayList;
import java.util.List;

public interface IService {
    List<?> get(String kind);
    <T> void save(Interval<T> i);
    <T, E extends Interval<T>> List<E> mergeIntervals(List<ArrayList<T>> input, String name);
}
