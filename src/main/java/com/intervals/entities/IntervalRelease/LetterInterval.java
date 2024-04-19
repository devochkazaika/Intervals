package com.intervals.entities.IntervalRelease;

import com.intervals.entities.Interval;
import com.intervals.exception.service.ServiceExceptionFabric;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.HashMap;


@Entity
@Table(name = "letters")
@NoArgsConstructor
/**
 * Класс для представления интервала строк.
 */
public class LetterInterval extends Interval<String> {

    /**
     * Создает интервал строк.
     *
     * @param start начальное значение интервала
     * @param ended конечное значение интервала
     * @throws IllegalArgumentException если значения не являются строками
     */
    public LetterInterval(String start, String ended) throws IllegalArgumentException {
        super(start, ended);
    }

    /**
     * Словарь для обозначения элементов и их значимости.
     * Регистр не имеет значения. Словарь содержит только буквы (без знаков).
     */
    private static HashMap<String, Integer> mapLetters = new HashMap<>(){{
        char t = 'a';
        int i = 0;
        int step = 'A' - 'a';
        while (t != 'z' + 1) {
            // Добавление маленькой буквы
            put(Character.toString(t), i);
            // Добавление большой буквы с тем же значением, что и у маленькой
            put(Character.toString(t + step), i);
            t++;
            i++;
        }
    }};

    @Override
    public void setStart(String start) throws IllegalArgumentException {
        if (!mapLetters.containsKey(start)){
            throw ServiceExceptionFabric.illegalLetterException(start);
        }
        else{
            super.setStart(start);
        }
    }
    @Override
    public void setEnded(String end) throws IllegalArgumentException {
        if (!mapLetters.containsKey(end)){
            throw ServiceExceptionFabric.illegalLetterException(end);
        }
        else{
            if (mapLetters.get(getStart()) > mapLetters.get(end)) throw ServiceExceptionFabric.illegalLetterException(end);
            super.setEnded(end);
        }
    }

    @Override
    public boolean mergeWith(Interval<String> value) {
        if (mapLetters.get(getStart()) <= mapLetters.get(value.getStart())){
            if (mapLetters.get(getEnded()) >= mapLetters.get(value.getStart()) &&
                    mapLetters.get(getEnded()) <= mapLetters.get(value.getEnded())){
                        this.setEnded(value.getEnded());
                        return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Interval<String> object) {
        int first = mapLetters.get(this.getStart());
        int second = mapLetters.get(object.getStart());
        if (first > second){
            return 1;
        }
        else{
            return -1;
        }
    }

}
