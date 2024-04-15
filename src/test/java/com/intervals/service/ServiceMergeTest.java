package com.intervals.service;

import com.intervals.entities.Interval;
import com.intervals.entities.IntervalRelease.DigitsInterval;
import com.intervals.repository.DigitRepository;
import com.intervals.repository.LetterRepository;
import com.intervals.service.impl.IntervalsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ServiceMergeTest {
    @InjectMocks
    private IntervalsService service;
    @Mock
    private DigitRepository digitRepository;
    @Mock
    private LetterRepository letterRepository;
    @Test()
    public void exception_test1(){
        assertThrows(IllegalArgumentException.class, ()->
                service.mergeIntervals(new ArrayList<ArrayList<Interval<Integer>>>(){
                    {
                        add(new ArrayList<Interval<Integer>>(){{
                                DigitsInterval.builder()
                                .start(1).ended(10).
                                        build();
                            }}
                        );
                    }
                }, "noname"));
    }

}
