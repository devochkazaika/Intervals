package com.intervals.service.impl;

import com.intervals.entities.Interval;
import com.intervals.entities.IntervalRelease.DigitInterval;
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

    /**
     * Создает интервал на основе входных данных.
     *
     * @param input список значений интервала
     * @param name  тип интервала (digits или letters)
     * @param <T>   тип данных интервала
     * @return созданный интервал
     * @throws IllegalTypeException если тип интервала недопустим
     */
    @SuppressWarnings("unchecked")
    private <T> Interval<T> createInterval(List<T> input, String name) throws IllegalTypeException {
        if (input.size() > 2) throw ServiceExceptionFabric.illegalTypeException(name);
        T start = input.get(0);
        T end = input.get(1);
        return switch (name) {
            case ("digits") -> (Interval<T>) new DigitInterval(start, end);
            case ("letters") -> (Interval<T>) new LetterInterval((String) start, (String) end);
            default -> throw ServiceExceptionFabric.illegalTypeException(name);
        };
    }

    /**
     * Объединяет список интервалов.
     *
     * @param arrayInterval список интервалов для объединения
     * @param <T>            тип интервалов
     * @param <E>            тип значений интервалов
     * @return список объединенных интервалов
     */
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

        // Проходим по остальным интервалам в списке
        for (int i = 1; i < arrayInterval.size(); i++) {
            Interval<E> elem = arrayInterval.get(i);
            if (!prevElem.mergeWith(elem)) {
                // Если текущий интервал не пересекается с предыдущим, добавляем его в ответ
                answer.add((T) elem);
                prevElem = elem;
            }
        }

        return answer;
    }

    /**
     * Объединяет список интервалов и сохраняет результат в базе данных.
     *
     * @param input список списков значений интервалов для объединения
     * @param name  имя типа интервала
     * @param <T>   тип значений интервалов
     * @param <E>   тип интервалов
     * @return список объединенных интервалов
     * @throws IllegalArgumentException если происходит некорректная типизация или другая ошибка при создании интервалов
     */
    @SuppressWarnings("unchecked")
    public <T, E extends Interval<T>> List<E> mergeIntervals(List<ArrayList<T>> input, String name)
            throws IllegalArgumentException {

        // Создание списка для хранения интервалов для последующей обработки
        ArrayList<E> arrayInterval = new ArrayList<>();

        // Сохранение в массив интервалов для последующей обработки
        input.forEach(i -> {
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
        merge.forEach(repoService::save);

        return merge;
    }
}
