package com.intervals.service;

import com.intervals.entities.IntervalRelease.DigitsInterval;
import com.intervals.entities.IntervalRelease.LetterInterval;
import com.intervals.repository.DigitRepository;
import com.intervals.repository.LetterRepository;
import com.intervals.service.impl.IntervalsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceWorkWithRepository {
    @InjectMocks
    private IntervalsService service;
    @Mock
    private DigitRepository digitRepository;
    @Mock
    private LetterRepository letterRepository;
    @Test
    void getMinDigits() throws Exception {
        when(digitRepository.getMinInterval()).thenReturn(DigitsInterval.builder()
                .start(1).ended(6)
                .build());
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 6));
        Assertions.assertEquals(service.get("digits"), expected);
    }
    @Test
    void getMinLetters() throws Exception {
        Mockito.when(letterRepository.getMinInterval()).thenReturn(LetterInterval.builder()
                .start("a").ended("c")
                .build());
        List<String> expected = new ArrayList<>(Arrays.asList("a", "c"));
        Assertions.assertEquals(service.get("letters"), expected);
    }
}
