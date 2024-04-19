package com.intervals.service;

import com.intervals.entities.Interval;

import java.util.List;

public interface IRepository {
    <T> List<T> get(String kind);
    <T> void save(Interval<T> i);
}
