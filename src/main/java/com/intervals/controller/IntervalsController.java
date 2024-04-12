package com.intervals.controller;

import com.intervals.service.impl.IntervalsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/intervals")
public class IntervalsController {

    private final IntervalsService usersService;
    @GetMapping("/min")
    public List<?> get(@RequestParam String kind){
        return usersService.get(kind);
    }

    @PostMapping("/merge")
    public <T> void mergeIntervals(@RequestParam String kind, @RequestBody ArrayList<ArrayList<T>> array){
        usersService.mergeIntervals(array, kind);
    }
}
