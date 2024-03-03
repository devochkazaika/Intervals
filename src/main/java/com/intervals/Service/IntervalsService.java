package com.intervals.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IntervalsService implements IService {
    @Autowired
    private final IntervalseRepository intervalseRepository;
    @Override
    public void get(){
        System.out.println("AAA");
    }
}
