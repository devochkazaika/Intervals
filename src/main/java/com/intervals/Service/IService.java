package com.intervals.Service;


import com.intervals.Model.Interval;

import java.util.ArrayList;
import java.util.List;

public interface IService {
    List<?> get(String kind);
    <T> void save(Interval<T> i);
    <T, E extends Interval<T>> void mergeIntervals(ArrayList<ArrayList<T>> input, String name);
}
