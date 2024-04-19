package com.intervals.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Абстрактный класс для представления интервалов.
 *
 * @param <T> тип значений интервала
 */
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
//конструктор без аргументов для Jpa
@NoArgsConstructor
public abstract class Interval<T> implements Comparable<Interval<T>>, MergeAble<T> {

    /** Уникальный идентификатор интервала. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /** Начальное значение интервала. */
    @Column(name = "start")
    T start;

    /** Конечное значение интервала. */
    @Column(name = "ended")
    T ended;

    /**
     * Создает интервал с указанными начальным и конечным значениями.
     *
     * @param start начальное значение интервала
     * @param end   конечное значение интервала
     */
    public Interval(T start, T end) {
        setStart(start);
        setEnded(end);
    }

    /**
     * Сравнивает данный интервал с другим.
     *
     * @param object интервал для сравнения
     * @return результат сравнения
     */
    @Override
    public abstract int compareTo(Interval<T> object);

    /**
     * Объединяет данный интервал с другим.
     *
     * @param value интервал для объединения
     * @return true, если интервалы успешно объединены; false в противном случае
     */
    @Override
    public abstract boolean mergeWith(Interval<T> value);

    /**
     * Возвращает строковое представление интервала.
     *
     * @return строковое представление интервала
     */
    @Override
    public String toString() {
        return "{start = " + start.toString() + ", ended = " + ended.toString() + "}";
    }
}
