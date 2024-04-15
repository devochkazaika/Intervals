package com.intervals.entities.IntervalRelease;

import com.intervals.entities.Interval;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "digits")
@SuperBuilder
@NoArgsConstructor
public class DigitsInterval extends Interval<Integer>{

    @Override
    public int compareTo(Interval<Integer> object) {
        if (this.getStart() > object.getStart()){
            return 1;
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean mergeWith(Interval<Integer> value) {
        if (getStart() <= value.getStart()){
            if (getEnded() >= value.getStart() && getEnded() <= value.getEnded()){
                setEnded(value.getEnded());
                return true;
            }
        }
        return false;
    }

    @Override
    public void setEnded(Integer value){
        if (getEnded() < value){
            super.setEnded(value);
        }
        super.setEnded(value);
    }
//    @Override
//    public int canMerge(Interval<Integer> object) {
//        if (this.getEnded() >= object.getStart()){
//            return 1;
//        }
//        else{
//            return -1;
//        }
//    }
}
