package com.intervals;

import com.intervals.Service.impl.IntervalsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class IntervalControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IntervalsService intervalsService;
	@Test
	void testFromTask(){

	}

}
