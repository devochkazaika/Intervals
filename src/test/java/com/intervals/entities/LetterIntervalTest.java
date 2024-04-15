package com.intervals.entities;

import com.intervals.entities.IntervalRelease.LetterInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LetterIntervalTest {
    @Test
    public void test1(){
        LetterInterval t1 = LetterInterval.builder()
                .start("a")
                .ended("f")
                .build();
        LetterInterval t2 = LetterInterval.builder()
                .start("d")
                .ended("j")
                .build();
        Assertions.assertEquals(-1, t1.compareTo(t2));
    }
}
