package com.intervals.service.impl;

import com.intervals.controller.IntervalsDTO.FactoryDTO;
import com.intervals.entities.Interval;
import com.intervals.entities.IntervalRelease.DigitsInterval;
import com.intervals.entities.IntervalRelease.LetterInterval;
import com.intervals.repository.DigitRepository;
import com.intervals.repository.LetterRepository;
import com.intervals.service.IService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class IntervalsService implements IService {
    private final DigitRepository intervalseRepository;
    private final LetterRepository stringRepository;

    @Override
    @SuppressWarnings("unchecked")
    public List<?> get(String kind) {
        if ("digits".equals(kind)) {
            return FactoryDTO.createGet(intervalseRepository.getMinInterval());
        } else if ("letters".equals(kind)) {
            return FactoryDTO.createGet(stringRepository.getMinInterval());
        }
        return null;
    }

    @Override
    public <T> void save(Interval<T> i) {
        if (i instanceof DigitsInterval) {
            intervalseRepository.save((DigitsInterval) i);
        } else if (i instanceof LetterInterval) {
            stringRepository.save((LetterInterval) i);
        }
    }

    private  <T extends Interval<E>, E> ArrayList<T> merge(ArrayList<T> arrayInterval) {
        Collections.sort(arrayInterval);

        ArrayList<T> answer = new ArrayList<>();
        answer.add(arrayInterval.get(0));
        //Для прохода по списку запоминаем последний в списке(то есть first)
        //И элемент с которым сравниваем(то есть second)
        Interval<E> first = answer.get(0);
        Interval<E> second;
        for (int i = 1; i < arrayInterval.size(); i++) {
            second = arrayInterval.get(i);
            if (first.canMerge(second) == 1) {
                first.setEnded(second.getEnded());
            } else {
                //Меняем местами, так как след интервал уже не пересекается
                first = second;
                answer.add(arrayInterval.get(i));
            }
        }
        return answer;
    }

    @SuppressWarnings("unchecked")
    public <T, E extends Interval<T>> List<E> mergeIntervals(List<ArrayList<T>> input, String name) {
        ArrayList<E> arrayInterval = new ArrayList<>();
        if (name.equals("digits")) {
            for (ArrayList<T> i : input) {
                arrayInterval.add((E) DigitsInterval.builder()
                        .start((Integer) i.get(0))
                        .ended((Integer) i.get(1))
                        .build());
            }
        } else if (name.equals("letters")) {
            for (ArrayList<T> i : input) {
                arrayInterval.add((E) LetterInterval.builder()
                        .start((String) i.get(0))
                        .ended((String) i.get(1))
                        .build());
            }
        }
        //Сохранение в бд, смердженных списков
        for (Interval<T> i : merge(arrayInterval)) {
            save(i);
        }
        return merge(arrayInterval);
    }
}
