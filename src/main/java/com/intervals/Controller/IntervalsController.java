package com.intervals.Controller;

import com.intervals.Service.IntervalsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/intervals")
public class IntervalsController {

    private final IntervalsService usersService;
    @PostMapping("/merge")
    public void get(String kind, @RequestParam ArrayList<Object> array){
        if (kind.equals("kind")){
            usersService.get();
            System.out.println(array);
        }
    }
}
