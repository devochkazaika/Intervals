package com.intervals.controller;

import com.intervals.service.IRepository;
import com.intervals.service.impl.IntervalRepositoryService;
import com.intervals.service.impl.IntervalsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/intervals")
public class IntervalsController {

    private final IntervalsService mergeService;
    private final IntervalRepositoryService repoService;
    @GetMapping("/min")
    public ResponseEntity<?> get(@RequestParam String kind){
        try {
            List<?> objects = repoService.get(kind);
            if (objects == null || objects.isEmpty()) {
                return ResponseEntity.ok("No minimum intervals found.");
            } else {
                return ResponseEntity.ok(objects);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/merge")
    public <T> ResponseEntity<?> mergeIntervals(@RequestParam String kind, @RequestBody ArrayList<ArrayList<T>> array){
        try {
            mergeService.mergeIntervals(array, kind);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
