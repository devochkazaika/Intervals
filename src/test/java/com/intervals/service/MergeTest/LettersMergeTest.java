package com.intervals.service;

import com.intervals.entities.IntervalRelease.DigitsInterval;
import com.intervals.entities.IntervalRelease.LetterInterval;
import com.intervals.repository.DigitRepository;
import com.intervals.repository.LetterRepository;
import com.intervals.service.impl.IntervalsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LettersMergeTest {
    @InjectMocks
    private IntervalsService service;
    @Mock
    private IRepository repositoryService;
    @Mock
    private LetterRepository letterRepository;

    void testArray(List<?> t, List<?> k){
        IntStream.range(0, t.size()).forEach(x-> {
            Assertions.assertEquals(t.get(x), k.get(x));
        });
    }
    private void testLetters(String[][] array, String[][] mergedArray){
        List<LetterInterval> expected = Arrays.stream(mergedArray).map(x ->
            new LetterInterval(x[0], x[1])).collect(Collectors.toList());

        List<ArrayList<String>> listOfLists = Arrays.stream(array).map(x -> {
                    return new ArrayList<>(Arrays.asList(x[0], x[1]));
                }
        ).collect(Collectors.toList());

        var Actual = service.mergeIntervals(listOfLists, "letters");
        System.out.println(Actual);
        testArray(expected, Actual);
    }
    @Test
    void merge_letters_Test(){
        String[][] array = new String[][]{{"a", "f"}, {"d", "j"}, {"r", "z"}};
        String[][] mergedArray = new String[][]{{"a", "j"}, {"r", "z"}};
        testLetters(array, mergedArray);

    }
    @Test
    void mergeLetterIntervals_Test2() {
        String[][] intervals = {{"B", "e"}, {"D", "g"}, {"h", "k"}};
        String[][] expected = {{"B", "g"}, {"h", "k"}};
        testLetters(intervals, expected);
    }
    @Test
    void mergeLetterIntervals_Test3() {
        String[][] intervals = {{"C", "I"}, {"g", "l"}, {"m", "Q"}};
        String[][] expected = {{"C", "l"}, {"m", "Q"}};
        testLetters(intervals, expected);
    }
    @Test
    void mergeLetterIntervals_Test4() {
        String[][] intervals = {{"a", "c"}, {"b", "e"}, {"d", "h"}};
        String[][] expected = {{"a", "h"}};
        testLetters(intervals, expected);
    }
    @Test
    void mergeLetterIntervals_Test5() {
        String[][] intervals = {{"p", "t"}, {"r", "v"}, {"w", "z"}};
        String[][] expected = {{"p", "v"}, {"w", "z"}};
        testLetters(intervals, expected);
        intervals = new String[][]{{"p", "T"}, {"r", "V"}, {"w", "Z"}};
        expected = new String[][]{{"p", "V"}, {"w", "Z"}};
        testLetters(intervals, expected);
    }
    /*
        a < e
     */
//    @Test
//    void NoIntervals_Test7() {
//        String[][] intervals = {{"a", "d"}};
//        String[][] expected = {{"a", "d"}};
//        testLetters(intervals, expected);
//        intervals = new String[][]{{"c", "f"}};
//        expected = new String[][]{{"c", "f"}};
//        testLetters(intervals, expected);
//    }
//    @Test
//    void NoIntervals_Test8() {
//        String[][] intervals = {{"a", "z"}};
//        String[][] expected = {{"a", "z"}};
//        testLetters(intervals, expected);
//        intervals = new String[][]{{"o", "s"}};
//        expected = new String[][]{{"q", "m"}};
//        testLetters(intervals, expected);
//    }

}
