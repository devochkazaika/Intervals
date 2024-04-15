package com.intervals.service.impl;

import com.intervals.controller.IntervalsDTO.FactoryDTO;
import com.intervals.entities.Interval;
import com.intervals.entities.IntervalRelease.DigitsInterval;
import com.intervals.entities.IntervalRelease.LetterInterval;
import com.intervals.exception.service.ServiceExceptionFabric;
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

    @SuppressWarnings("unchecked")
    private  <T extends Interval<E>, E> ArrayList<T> merge(ArrayList<T> arrayInterval) {
        //Сортируем для упрощения алгоритма
        Collections.sort(arrayInterval);

        ArrayList<T> answer = new ArrayList<>();
        answer.add(arrayInterval.get(0));
        //Для прохода по списку запоминаем предыдущий в списке(то есть prevElem)
        Interval<E> prevElem = answer.get(0);
        //И элемент с которым сравниваем(то есть second)
        Interval<E> elem;
        for (int i = 1; i < arrayInterval.size(); i++) {
            elem = arrayInterval.get(i);
            if(!prevElem.mergeWith(elem)){
                //Меняем местами, так как след интервал уже не пересекается
                answer.add(arrayInterval.get(i));
                prevElem = elem;
            }
        }
        return answer;
    }
    @SuppressWarnings("unchecked")
    private <T> Interval<T> createInterval(T start, T end, String name) throws Exception {
        switch (name){
            case ("digits"):
                return (Interval<T>) DigitsInterval.builder()
                        .start((Integer) start)
                        .ended((Integer) end)
                        .build();
            case("letters"):
                return (Interval<T>) LetterInterval.builder()
                        .start((String) start)
                        .ended((String) end)
                        .build();
            default:
                throw ServiceExceptionFabric.illegalTypeException(name);
        }
    }

    @SuppressWarnings("unchecked")
    public <T, E extends Interval<T>> List<E> mergeIntervals(List<ArrayList<T>> input, String name)
    throws IllegalArgumentException{

        ArrayList<E> arrayInterval = new ArrayList<>();
        //Сохранение в массив интервалов для последующей обработки
        input.stream().forEach(i->{
                try {
                    arrayInterval.add(
                            (E) createInterval(i.get(0), i.get(1), name)
                    );
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
                }
        );

        //мердж интервалов
        ArrayList<E> merge = merge(arrayInterval);
        //Сохранение в бд, смердженных списков
        for (Interval<T> i : merge) {
            save(i);
        }
        return merge;
    }
}