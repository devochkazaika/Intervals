package com.intervals;

import com.intervals.Model.Interval;
import com.intervals.Model.IntervalRelease.DigitInterval;
import com.intervals.Model.IntervalRelease.LetterInterval;
import com.intervals.Repository.DigitRepository;
import com.intervals.Service.impl.IntervalsService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ServiceTest {
	@Mock
	private DigitRepository digitRepository;

	@InjectMocks
	private IntervalsService service;

	@Test
	@SuppressWarnings("unchecked")
	void testFromTask1() throws Exception{
		Interval<Integer> test1 = DigitInterval.builder()
				.start(1).ended(4).build();
		Interval<Integer> test2 = DigitInterval.builder()
				.start(3).ended(6).build();
		Interval<Integer> test3 = DigitInterval.builder()
				.start(8).ended(10).build();
		ArrayList<Interval<Integer>> array = new ArrayList<Interval<Integer>>(){{
			add(test1);
			add(test2);
			add(test3);
		}};
		List<?> a = service.merge(array);
		Interval<Integer> answer = DigitInterval.builder()
				.start(1).ended(6).build();
		Interval<Integer> answer2 = DigitInterval.builder()
				.start(8).ended(10).build();
		assertEquals(a, new ArrayList<Interval>(){{add(answer);add(answer2);}});
	}
	@Test
	@SuppressWarnings("unchecked")
	void testFromTask2() throws Exception{
		Interval<String> test1 = LetterInterval.builder()
				.start("a").ended("f").build();
		Interval<String> test2 = LetterInterval.builder()
				.start("d").ended("j").build();
		Interval<String> test3= LetterInterval.builder()
				.start("r").ended("z").build();
		ArrayList<Interval<String>> array = new ArrayList<Interval<String>>(){{
			add(test1);
			add(test2);
			add(test3);
		}};
		List<?> a = service.merge(array);
		Interval<String> answer = LetterInterval.builder()
				.start("a").ended("j").build();
		Interval<String> answer2 = LetterInterval.builder()
				.start("r").ended("z").build();
		assertEquals(a, new ArrayList<Interval>(){{add(answer);add(answer2);}});
	}
	@Test
	@SuppressWarnings("unchecked")
	void testFromTask() throws Exception{
		Interval<Integer> test1 = DigitInterval.builder()
				.start(0).ended(20).build();
		Interval<Integer> test2 = DigitInterval.builder()
				.start(10).ended(25).build();
		ArrayList<Interval<Integer>> array = new ArrayList<Interval<Integer>>(){{
			add(test1);
			add(test2);
		}};
		List<?> a = service.merge(array);
		Interval<Integer> answer = DigitInterval.builder()
				.start(0).ended(25).build();
		assertEquals(a, new ArrayList<Interval>(){{add(answer);}});
	}
	@Test
	@SuppressWarnings("unchecked")
	void test4() throws Exception{
		Interval<Integer> test1 = DigitInterval.builder()
				.start(0).ended(20).build();
		Interval<Integer> test2 = DigitInterval.builder()
				.start(2).ended(5).build();
		Interval<Integer> test3 = DigitInterval.builder()
				.start(18).ended(50).build();
		Interval<Integer> test4 = DigitInterval.builder()
				.start(-2).ended(-1).build();
		ArrayList<Interval<Integer>> array = new ArrayList<Interval<Integer>>(){{
			add(test1);
			add(test2);
			add(test3);
			add(test4);
		}};
		List<?> a = service.merge(array);
		Interval<Integer> answer = DigitInterval.builder()
				.start(-2).ended(-1).build();
		Interval<Integer> answer2 = DigitInterval.builder()
				.start(0).ended(50).build();
		assertEquals(a, new ArrayList<Interval>(){{add(answer);
			add(answer2);
		}});
	}
	@Test
	@SuppressWarnings("unchecked")
	void test5() throws Exception{
		Interval<Integer> test1 = DigitInterval.builder()
				.start(0).ended(2).build();
		Interval<Integer> test2 = DigitInterval.builder()
				.start(2).ended(5).build();
		Interval<Integer> test3 = DigitInterval.builder()
				.start(5).ended(10).build();
		ArrayList<Interval<Integer>> array = new ArrayList<Interval<Integer>>(){{
			add(test1);
			add(test2);
			add(test3);
		}};
		List<?> a = service.merge(array);
		Interval<Integer> answer = DigitInterval.builder()
				.start(0).ended(10).build();
		assertEquals(a, new ArrayList<Interval>(){{add(answer);
		}});
	}

	@Test
	@SuppressWarnings("unchecked")
	void myTest1() throws Exception{
		Interval<String> test1 = LetterInterval.builder()
				.start("a").ended("b").build();
		Interval<String> test2 = LetterInterval.builder()
				.start("c").ended("d").build();
		Interval<String> test3= LetterInterval.builder()
				.start("n").ended("o").build();
		ArrayList<Interval<String>> array = new ArrayList<Interval<String>>(){{
			add(test1);
			add(test2);
			add(test3);
		}};
		List<?> a = service.merge(array);
		Interval<String> answer1 = LetterInterval.builder()
				.start("a").ended("b").build();
		Interval<String> answer2 = LetterInterval.builder()
				.start("c").ended("d").build();
		Interval<String> answer3 = LetterInterval.builder()
				.start("n").ended("o").build();
		assertEquals(a, new ArrayList<Interval>(){{add(answer1);
		add(answer2);
		add(answer3);}});
	}
}
