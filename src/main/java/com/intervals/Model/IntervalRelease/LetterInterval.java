package com.intervals.Model.IntervalRelease;

import com.intervals.Model.Interval;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;


@Entity
@Table(name = "stringIntervals")
@SuperBuilder
@NoArgsConstructor
public class LetterInterval extends Interval<String> {
    //Словарь для обозначения элементов и какая у них значимость, регистр не имеет значения
    //Словарь содержит только буквы(без каких-либо знаков), как и сказано в задании
    private static HashMap<String, Integer> mapLetters = new HashMap<>(){{
        char t = 'a';
        int i = 0;
        int step = 'A' - 'a';
        while (t != 'z'+1){
            //Добавление МАЛЕНЬКОЙ буквы
            put(Character.toString(t), i);
            //Добавление БОЛЬШОЙ буквы с тем же i, что и у маленькой
            put(Character.toString(t + step), i);
            t++;
            i++;
        }
    }};
    @Override
    public int compareTo(Interval<String> object) {
        Integer first = mapLetters.get(this.getStart());
        Integer second = mapLetters.get(object.getStart());
        if (first > second){
            return 1;
        }
        else{
            return -1;
        }
    }
    @Override
    public int canMerge(Interval<String> object){
        if (this.getEnded().charAt(0) >= object.getStart().charAt(0)){
            return 1;
        }
        else{
            return -1;
        }
    }
}
