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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceWorkWithRepository {
    @InjectMocks
    private IntervalsService service;
    @Mock
    private IRepository repositoryService;
    @Test
    void getMinDigits() throws Exception {
        when(repositoryService.get("digits")).thenReturn(new ArrayList<>(
                Arrays.asList(1, 6)
        ));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 6));
        Assertions.assertEquals(repositoryService.get("digits"), expected);
    }
    @Test
    void getMinLetters() throws Exception {
        Mockito.when(repositoryService.get("letters")).thenReturn(new ArrayList<>(Arrays.asList("a", "c")));
        List<String> expected = new ArrayList<>(Arrays.asList("a", "c"));
        Assertions.assertEquals(repositoryService.get("letters"), expected);
    }
}
