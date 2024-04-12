package com.intervals;

import com.intervals.Model.Interval;
import com.intervals.Model.IntervalRelease.DigitInterval;
import com.intervals.Model.IntervalRelease.LetterInterval;
import com.intervals.Repository.DigitRepository;
import com.intervals.Service.impl.IntervalsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ExceptionTest {
    @Mock
    private DigitRepository digitRepository;

    @InjectMocks
    private IntervalsService service;
    @Test
    @SuppressWarnings("unchecked")
    void test1() throws Exception{
        Assertions.assertThrows(Exception.class,
                () ->DigitInterval.builder().start(20).ended(10).build().normalData());
        Assertions.assertThrows(Exception.class,
                () -> LetterInterval.builder().start("r").ended("b").build().normalData());
    }
    @Test
    @SuppressWarnings("unchecked")
    void test2() throws Exception{
        Assertions.assertThrows(Exception.class,
                () -> LetterInterval.builder().start(",").ended("b").build().normalData());
        Assertions.assertThrows(Exception.class,
                () -> LetterInterval.builder().start("a").ended("!").build().normalData());
    }

}
