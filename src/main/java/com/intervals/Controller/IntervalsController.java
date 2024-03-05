package com.intervals.Controller;

import com.intervals.Model.Interval;
import com.intervals.Model.IntervalRelease.DecimalInterval;
import com.intervals.Model.Pair;
import com.intervals.Service.impl.IntervalsService;
import lombok.AllArgsConstructor;
import org.hibernate.type.descriptor.java.AbstractArrayJavaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/intervals")
public class IntervalsController {

    private final IntervalsService usersService;
    //Исправить инты на дженерики
    //Добавить отдельную функцию для мерджа и переместить в сервис

    @GetMapping("/min")
    public <T> Interval<T> get(@RequestParam String kind){
        return usersService.get(kind);
    }

    @PostMapping("/merge")
    public <T> void mergeIntervals(@RequestParam String kind, @RequestBody ArrayList<ArrayList<T>> array){
        usersService.mergeIntervals(kind, array);
    }
}
