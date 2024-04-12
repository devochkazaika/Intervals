package com.intervals.Service.impl;

import com.intervals.Controller.IntervalsDTO.FactoryDTO;
import com.intervals.Model.Interval;
import com.intervals.Model.IntervalRelease.DigitInterval;
import com.intervals.Model.IntervalRelease.LetterInterval;
import com.intervals.Repository.DigitRepository;
import com.intervals.Repository.LetterRepository;
import com.intervals.Service.IService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class IntervalsService implements IService {
    //    @Autowired
    private final DigitRepository intervalseRepository;
    private final LetterRepository stringRepository;

    @Override
    @SuppressWarnings("unchecked")
    public List<?> get(String kind) throws Exception {
        if ("digits".equals(kind)) {
            return FactoryDTO.createGet(intervalseRepository.getMinInterval().orElse(null));
        }
        else if ("letters".equals(kind)) {
            return FactoryDTO.createGet(stringRepository.getMinInterval().orElse(null));
        }
        else{
            throw new Exception("this kind doesnt exist");
        }

    }

    @Override
    public <T> void save(Interval<T> i) throws Exception {
        if (i instanceof DigitInterval) {
            intervalseRepository.save((DigitInterval) i);
        } else if (i instanceof LetterInterval) {
            stringRepository.save((LetterInterval) i);
        }
        else{
            throw new Exception("Unsupported type to save");
        }
    }

    public <T extends Interval<E>, E> ArrayList<T> merge(ArrayList<T> arrayInterval) throws Exception {
        Collections.sort(arrayInterval);

        ArrayList<T> answer = new ArrayList<>();
        answer.add(arrayInterval.get(0));
        //Для прохода по списку запоминаем последний в списке(то есть first)
        //И элемент с которым сравниваем(то есть second)
        Interval<E> last = answer.get(0);
        Interval<E> second;
        for (int i = 1; i < arrayInterval.size(); i++) {
            second = arrayInterval.get(i);
            switch (last.canMerge(second)){
                case 1:
                    last.setEnded(second.getEnded());
                    break;
                case -1:
                    answer.add(arrayInterval.get(i));
                    last = answer.get(answer.size()-1);
                    break;
            }
        }
        return answer;
    }

    @SuppressWarnings("unchecked")
    public <T, E extends Interval<T>> void mergeIntervals(ArrayList<ArrayList<T>> input, String name) throws Exception {
        ArrayList<E> arrayInterval = new ArrayList<>();
        if (name.equals("digits")) {
            for (ArrayList<T> i : input) {
                arrayInterval.add((E) DigitInterval.builder()
                        .start((Integer) i.get(0))
                        .ended((Integer) i.get(1))
                        .build().normalData());

            }
        } else if (name.equals("letters")) {
            for (ArrayList<T> i : input) {
                arrayInterval.add((E) LetterInterval.builder()
                        .start((String) i.get(0))
                        .ended((String) i.get(1))
                        .build().normalData());
            }
        }
        else{
            throw new Exception("this kind doesnt exits");
        }
        //Сохранение в бд, смердженных списков
        for (Interval<T> i : merge(arrayInterval)) {
            save(i);
        }
    }
}
