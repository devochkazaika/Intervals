package com.intervals.Controller;

import com.intervals.Service.impl.IntervalsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/intervals")
public class IntervalsController {

    private final IntervalsService usersService;
    @GetMapping("/min")
    public ResponseEntity<?> get(@RequestParam("kind") String kind){
        try {
            List<?> interval = usersService.get(kind);
            if (interval == null){
                return new ResponseEntity<>("DB is empty", HttpStatus.OK);
            }
            return new ResponseEntity<>(interval, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/merge")
    public <T> ResponseEntity<?> mergeIntervals(@RequestParam("kind") String kind, @RequestBody ArrayList<ArrayList<T>> array) throws Exception {
        try {
            usersService.mergeIntervals(array, kind);;
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
