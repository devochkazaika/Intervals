package com.intervals.exception.service;

import com.intervals.entities.Interval;
import com.intervals.exception.service.types.IllegalTypeException;

/**
 * Фабрика исключений, используемая для создания исключений, связанных с некорректными значениями и интервалами.
 */
public class ServiceExceptionFabric {

    /**
     * Создает исключение IllegalTypeException для указанного типа.
     *
     * @param type тип, вызывающий исключение
     * @return IllegalTypeException с сообщением об ошибке
     */
    public static IllegalTypeException illegalTypeException(String type) {
        return new IllegalTypeException("type " + type + " is not supported");
    }

    /**
     * Создает исключение IllegalArgumentException для недопустимой буквы.
     *
     * @param value значение, содержащее недопустимую букву
     * @return IllegalArgumentException с сообщением об ошибке
     */
    public static IllegalArgumentException illegalLetterException(String value) {
        return new IllegalArgumentException("illegal letter in " + value);
    }

    /**
     * Создает исключение IllegalArgumentException для недопустимого интервала.
     *
     * @param value интервал, вызывающий исключение
     * @return IllegalArgumentException с сообщением об ошибке
     */
    public static IllegalArgumentException illegalArgumentException(Interval<?> value) {
        return new IllegalArgumentException("illegal interval = " + value.toString());
    }

    /**
     * Создает исключение IllegalArgumentException для недопустимого интервала с указанными начальным и конечным значениями.
     *
     * @param start начальное значение интервала
     * @param ended конечное значение интервала
     * @param <T>   тип начального значения
     * @param <E>   тип конечного значения
     * @return IllegalArgumentException с сообщением об ошибке
     */
    public static <T, E> IllegalArgumentException illegalArgumentException(T start, E ended) {
        return new IllegalArgumentException("illegal interval = {" +
                "start = " + start.toString() + ", ended = " + ended + "}");
    }
}
