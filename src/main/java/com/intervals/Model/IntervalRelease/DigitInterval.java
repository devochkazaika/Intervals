package com.intervals.Model.IntervalRelease;

import com.intervals.Model.Interval;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "digits")
@SuperBuilder
@NoArgsConstructor
public class DigitInterval extends Interval<Integer>{
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
    public int canMerge(Interval<Integer> object) throws Exception {
        if (this.getEnded() >= object.getStart() &&
            this.getEnded() < object.getEnded()){
                return 1;
        }
        else if (this.getEnded() > object.getEnded() &&
            this.getStart() <= object.getStart()){
            return 0;
        }
        else {
            return -1;
        }
    }
    @Override
    public Interval<Integer> normalData() throws Exception{
        if (this.getStart() > this.getEnded()){
            throw new Exception("Start more bigger then end");
        }
        return this;
    }
}
