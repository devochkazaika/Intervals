package com.intervals.Model.IntervalRelease;

import com.intervals.Model.Interval;
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
    public int canMerge(Interval<Integer> object) {
        if (this.getEnded() >= object.getStart()){
            return 1;
        }
        else{
            return -1;
        }
    }
}
