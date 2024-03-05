package com.intervals.Model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class Pair <T> {
    T start;
    T end;
    public Pair(T start, T end){
        this.start = start;
        this.end = end;
    }
}
