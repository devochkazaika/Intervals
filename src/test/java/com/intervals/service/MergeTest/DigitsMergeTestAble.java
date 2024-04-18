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
class DigitsMergeTestAble {
    @InjectMocks
    private IntervalsService service;

    @Mock
    private IRepository repositoryService;
    @Mock
    private DigitRepository digitRepository;
    @Mock
    private LetterRepository letterRepository;
    @Test
    @Rollback(value = true)
    void saveTest() throws Exception {
        ArrayList<DigitsInterval> testDB = new ArrayList<>(
                Arrays.asList(  new DigitsInterval(10, 15),
                                new DigitsInterval(14, 17),
                                new DigitsInterval(124, 1235)));
        testDB.forEach(x -> {
            repositoryService.save(x);
        });
    }
    private void testDigits(int[][] array, int[][] mergedArray){
        List<DigitsInterval> expected = Arrays.stream(mergedArray).map(
                x -> new DigitsInterval(x[0], x[1])
                ).collect(Collectors.toList());
        List<ArrayList<Integer>> listOfLists = Arrays.stream(array).map(
                x -> new ArrayList<>(Arrays.asList(x[0], x[1]))
                ).collect(Collectors.toList());

        var Actual = service.mergeIntervals(listOfLists, "digits");
        System.out.println(Actual);
        testArray(expected, Actual);
    }
    @Test
    void merge_digits_Test1(){
        int[][] array = new int[][]{{1, 6}, {3, 6}, {8, 10}};
        int[][] mergedArray = new int[][]{{1, 6}, {8, 10}};
        testDigits(array, mergedArray);
    }
    @Test
    void merge_digits_Test2(){
        int[][] array = new int[][]{{-15, 90}, {3, 6}, {8, 10}};
        int[][] mergedArray = new int[][]{{-15, 90}};
        testDigits(array, mergedArray);
    }
    @Test
    void merge_digits_Test3(){
        int[][] array = new int[][]{{1, 1}, {1, 1}, {1, 1}};
        int[][] mergedArray = new int[][]{{1, 1}};
        testDigits(array, mergedArray);
    }
    @Test
    void merge_digits_Test4(){
        int[][] array = new int[][]{{1, 8}, {3, 12}, {11, 18}};
        int[][] mergedArray = new int[][]{{1, 18}};
        testDigits(array, mergedArray);
    }
    @Test
    void merge_digits_Test5(){
        int[][] array = new int[][]{{3, 8}, {7, 12}, {11, 16}};
        int[][] mergedArray = new int[][]{{3, 16}};
        testDigits(array, mergedArray);
    }
    @Test
    void merge_digits_Test6(){
        int[][] array = new int[][]{{1, 10}, {12, 20}, {22, 30}};
        int[][] mergedArray = new int[][]{{1, 10}, {12, 20}, {22, 30}};
        testDigits(array, mergedArray);
    }
    @Test
    void merge_digits_Test7(){
        int[][] array = new int[][]{{1, 5}, {6, 10}, {11, 15}};
        int[][] mergedArray = new int[][]{{1, 5}, {6, 10}, {11, 15}};
        testDigits(array, mergedArray);
    }
    @Test
    void merge_digits_Test8(){
        int[][] array = new int[][]{{2, 7}, {8, 12}, {10, 15}};
        int[][] mergedArray = new int[][]{{2, 7}, {8, 15}};
        testDigits(array, mergedArray);
    }
    void testArray(List<?> t, List<?> k){
        IntStream.range(0, t.size()).forEach(x-> {
            Assertions.assertEquals(t.get(x), k.get(x));
        });
    }
    private void testLetters(String[][] array, String[][] mergedArray){
        List<LetterInterval> expected = Arrays.stream(mergedArray)
                .map(x -> new LetterInterval(x[0], x[1]))
                .collect(Collectors.toList());

        List<ArrayList<String>> listOfLists = Arrays.stream(array)
                .map(x -> new ArrayList<>(Arrays.asList(x[0], x[1])))
                .collect(Collectors.toList());

        var Actual = service.mergeIntervals(listOfLists, "letters");
        testArray(expected, Actual);
    }
    @Test
    void merge_letters_Test(){
        String[][] array = new String[][]{{"a", "f"}, {"d", "j"}, {"r", "z"}};
        String[][] mergedArray = new String[][]{{"a", "j"}, {"r", "z"}};
        testLetters(array, mergedArray);

    }
    @Test
    void merge_letters_Test2(){
        String[][] array = new String[][]{{"a", "f"}, {"d", "j"}, {"r", "z"}};
        String[][] mergedArray = new String[][]{{"a", "j"}, {"r", "z"}};
        testLetters(array, mergedArray);

    }

}
