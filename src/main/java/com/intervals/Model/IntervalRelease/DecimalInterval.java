package com.intervals.Model.IntervalRelease;

import com.intervals.Model.Interval;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "decimalIntervals")
@Data
@SuperBuilder
@NoArgsConstructor
public class DecimalInterval extends Interval<Integer>{
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
