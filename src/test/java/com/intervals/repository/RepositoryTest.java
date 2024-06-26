package com.intervals.repository;

import com.intervals.entities.IntervalRelease.DigitInterval;
import com.intervals.service.impl.IntervalRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RepositoryTest {
    @Autowired
    private IntervalRepositoryService repoDigits;
    @Test
    public void NoIntervalsTest(){
        repoDigits.save(new DigitInterval(1, 4));
        repoDigits.save(new DigitInterval(1, 5));
        System.out.println(repoDigits.get("digits"));
        Assertions.assertEquals(repoDigits.get("digits"), null);
    }
}
