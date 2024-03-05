package com.intervals.Service;

import com.intervals.Model.Interval;

import java.util.ArrayList;

public interface IService {
    public <E, T extends Interval<E>> T get(String kind);
    <T> void save(Interval<T> i);
    <T extends Interval<E>, E> ArrayList<T> merge(ArrayList<T> arrayInterval);
}
