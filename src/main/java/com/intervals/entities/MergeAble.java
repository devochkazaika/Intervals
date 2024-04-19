package com.intervals.entities;

/**
 * Интерфейс, определяющий метод для объединения интервалов.
 *
 * @param <T> тип значений интервала
 */
public interface MergeAble<T> {

    /**
     * Объединяет данный интервал с другим.
     *
     * @param value интервал для объединения
     * @return true, если интервалы успешно объединены; false в противном случае
     */
    boolean mergeWith(Interval<T> value);
}