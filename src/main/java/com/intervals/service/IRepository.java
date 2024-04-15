package com.intervals.service;

import com.intervals.entities.Interval;

import java.util.List;
import java.util.Optional;

public interface IRepository {
    <T> List<T> get(String kind);
    <T> void save(Interval<T> i);
}
