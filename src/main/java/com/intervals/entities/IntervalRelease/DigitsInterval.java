package com.intervals.entities.IntervalRelease;

import com.intervals.entities.Interval;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "digits")
//конструктор без аргументов для Jpa
@NoArgsConstructor
public class DigitsInterval extends Interval<Long>{

    public DigitsInterval(Long st, Long en) throws IllegalArgumentException {
        super(st, en);
    }
    public DigitsInterval(Integer st, Integer en) throws IllegalArgumentException{
        super(st.longValue(), en.longValue());
    }

    @Override
    public int compareTo(Interval<Long> object) {
        if (this.getStart() > object.getStart()){
            return 1;
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean mergeWith(Interval<Long> value) {
        if (getStart() <= value.getStart()){
            if (getEnded() >= value.getStart() && getEnded() <= value.getEnded()){
                setEnded(value.getEnded());
                return true;
            }
        }
        return false;
    }

    @Override
    public void setEnded(Long value){
        if (getStart() > value) throw new IllegalArgumentException();
        else{
            super.setEnded(value);
        }
    }
    @Override
    public void setStart(Long value){
        super.setStart(value);
    }
}
