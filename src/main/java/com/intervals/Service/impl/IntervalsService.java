package com.intervals.Service.impl;

import com.intervals.Model.Interval;
import com.intervals.Model.IntervalRelease.DecimalInterval;
import com.intervals.Model.IntervalRelease.StringInterval;
import com.intervals.Repository.DecimalRepository;
import com.intervals.Repository.StringRepository;
import com.intervals.Service.IService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
@AllArgsConstructor
public class IntervalsService implements IService {
    @Autowired
    private final DecimalRepository intervalseRepository;
    private final StringRepository stringRepository;
    @Override
    @SuppressWarnings("unchecked")
    public <E, T extends Interval<E>> T get(String kind) {
        if ("digits".equals(kind)) {
            return (T) intervalseRepository.getMinInterval();
        }
        else if("letters".equals(kind)) {
            return (T) stringRepository.getMinInterval();
        }
        return null;
    }
    @Override
    public<T> void save(Interval<T> i){
        if (i instanceof DecimalInterval){
            intervalseRepository.save((DecimalInterval) i);
        }
        else if (i instanceof StringInterval){
            stringRepository.save((StringInterval) i);
        }
    }

    @Override
    public <T extends Interval<E>, E> ArrayList<T> merge(ArrayList<T> arrayInterval){
        Collections.sort(arrayInterval);

        ArrayList<T> answer = new ArrayList<>();
        answer.add(arrayInterval.get(0));
        //Для прохода по списку запоминаем последний в списке(то есть first)
        //И элемент с которым сравниваем(то есть second)
        Interval<E> first = answer.get(0);
        Interval<E> second;
        for (int i=1; i<arrayInterval.size(); i++){
            second = arrayInterval.get(i);
            if (first.canMerge(second) == 1){
                first.setEnded(second.getEnded());
            }
            else{
                //Меняем местами, так как след интервал уже не пересекается
                first = second;
                answer.add(arrayInterval.get(i));
            }
        }
        return answer;
    }

    public <T> void mergeIntervals(String kind, ArrayList<ArrayList<T>> array){
        if (kind.equals("digits")){
            ArrayList<DecimalInterval> arrayInterval = new ArrayList<>();
            for (ArrayList<T> i : array){
                arrayInterval.add(DecimalInterval.builder()
                        .start((Integer) i.get(0))
                        .ended((Integer) i.get(1))
                        .build());
            }
            for (DecimalInterval i : merge(arrayInterval)){
                save(i);
            }
        }
        if (kind.equals("letters")){
            ArrayList<StringInterval> arrayInterval = new ArrayList<>();
            for (ArrayList<T> i : array){
                arrayInterval.add(StringInterval.builder()
                        .start((String) i.get(0))
                        .ended((String) i.get(1))
                        .build());
            }
            for (StringInterval i : merge(arrayInterval)){
                save(i);
            }
        }
    }
}
