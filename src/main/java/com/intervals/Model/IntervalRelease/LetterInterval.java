package com.intervals.Model.IntervalRelease;

import com.intervals.Model.Interval;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;


@Entity
@Table(name = "letters")
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
    public int canMerge(Interval<String> object) throws Exception {
        if (mapLetters.get(this.getEnded()) >= mapLetters.get(object.getStart()) &&
            mapLetters.get(this.getEnded()) < mapLetters.get((object.getEnded()))){
            return 1;
        }
        else if (mapLetters.get(this.getEnded()) > mapLetters.get(object.getEnded()) &&
                mapLetters.get(this.getStart()) <= mapLetters.get((object.getStart()))){
            return 0;
        }
        else{
            return -1;
        }
    }
    @Override
    public Interval<String> normalData() throws Exception{
        if (mapLetters.get(this.getStart()) > mapLetters.get(this.getEnded())){
            throw new Exception("Start more bigger then end");
        }
        if (!(mapLetters.containsKey(this.getStart()))){
            throw new Exception("letter ".concat(this.getStart()).concat(" doesnt exist"));
        }
        if (!(mapLetters.containsKey(this.getEnded()))){
            throw new Exception("letter ".concat(this.getEnded()).concat(" doesnt exist"));
        }
        return this;
    }
}
