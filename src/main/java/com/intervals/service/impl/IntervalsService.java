package com.intervals.service.impl;

import com.intervals.entities.Interval;
import com.intervals.entities.IntervalRelease.DigitsInterval;
import com.intervals.entities.IntervalRelease.LetterInterval;
import com.intervals.exception.service.ServiceExceptionFabric;
import com.intervals.exception.service.types.IllegalTypeException;
import com.intervals.service.IRepository;
import com.intervals.service.IService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@ComponentScan
public class IntervalsService implements IService {

    private IRepository repoService;

    // Создание Интервалов, при последующем расширении - сделать фабрику
    @SuppressWarnings("unchecked")
    private <T> Interval<T> createInterval(List<T> input, String name) throws IllegalTypeException {
        if (input.size() > 2) throw ServiceExceptionFabric.illegalTypeException(name);
        T start = input.get(0);
        T end = input.get(1);
        return switch (name) {
            case ("digits") -> (Interval<T>) new DigitsInterval((Long) start, (Long) end);
            case ("letters") -> (Interval<T>) new LetterInterval((String) start, (String) end);
            default -> throw ServiceExceptionFabric.illegalTypeException(name);
        };
    }

    @SuppressWarnings("unchecked")
    private <T extends Interval<E>, E> ArrayList<T> merge(ArrayList<T> arrayInterval) {
        // Сортировка для упрощения алгоритма
        Collections.sort(arrayInterval);

        // Список для хранения объединенных интервалов
        ArrayList<T> answer = new ArrayList<>();

        // Добавляем первый интервал в ответ, так как он всегда остается неизменным
        answer.add(arrayInterval.get(0));

        // Для прохода по списку запоминаем предыдущий элемент (prevElem)
        Interval<E> prevElem = answer.get(0);

        // И элемент, с которым сравниваем (second)
        Interval<E> elem;

        // Проходим по остальным интервалам в списке
        for (int i = 1; i < arrayInterval.size(); i++) {
            elem = arrayInterval.get(i);
            if (!prevElem.mergeWith(elem)) {
                // Если текущий интервал не пересекается с предыдущим, добавляем его в ответ
                answer.add(arrayInterval.get(i));
                prevElem = elem;
            }
        }

        return answer;
    }

    @SuppressWarnings("unchecked")
    public <T, E extends Interval<T>> List<E> mergeIntervals(List<ArrayList<T>> input, String name)
            throws IllegalArgumentException {

        // Создание списка для хранения интервалов для последующей обработки
        ArrayList<E> arrayInterval = new ArrayList<>();

        // Сохранение в массив интервалов для последующей обработки
        input.stream().forEach(i -> {
            try {
                arrayInterval.add((E) createInterval(i, name));
            } catch (IllegalTypeException e) {
                // Если обнаружена некорректная типизация, выбрасываем IllegalArgumentException с соответствующим сообщением
                throw new IllegalArgumentException(e.getMessage());
            } catch (Exception e) {
                // Если произошла другая ошибка, выбрасываем IllegalArgumentException с сообщением, созданным через ServiceExceptionFabric
                throw ServiceExceptionFabric.illegalArgumentException(i.get(0), i.get(1));
            }
        });

        // Мердж интервалов
        ArrayList<E> merge = merge(arrayInterval);

        // Сохранение в базе данных смерженных списков
        for (Interval<T> i : merge) {
            repoService.save(i);
        }

        return merge;
    }
}
