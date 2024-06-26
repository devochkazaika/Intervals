package com.intervals.service.impl;

import com.intervals.controller.IntervalsDTO.FactoryDTO;
import com.intervals.entities.Interval;
import com.intervals.entities.IntervalRelease.DigitInterval;
import com.intervals.entities.IntervalRelease.LetterInterval;
import com.intervals.repository.DigitRepository;
import com.intervals.repository.LetterRepository;
import com.intervals.service.IRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IntervalRepositoryService implements IRepository {
    private final DigitRepository intervalseRepository;
    private final LetterRepository stringRepository;
    @Override
    @SuppressWarnings("unchecked")
    public List<?> get(String kind) {
        if ("digits".equals(kind)) {
            return FactoryDTO.createGet(intervalseRepository.getMinInterval().orElse(null));
        } else if ("letters".equals(kind)) {
            return FactoryDTO.createGet(stringRepository.getMinInterval().orElse(null));
        }
        return null;
    }

    @Override
    public <T> void save(Interval<T> i) {
        if (i instanceof DigitInterval) {
            intervalseRepository.save((DigitInterval) i);
        } else if (i instanceof LetterInterval) {
            stringRepository.save((LetterInterval) i);
        }
    }
}
