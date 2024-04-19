package com.intervals.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intervals.service.impl.IntervalsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class IntervalControllerTest {
    @Autowired
    @InjectMocks
    private IntervalsController controller;
    @Mock
    private  IntervalsService service;
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();
    }
    @Test
    void GetTest1() throws Exception {

    }
    @Test
    void PostTest1() throws Exception {
        ArrayList<ArrayList<Integer>> t = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList(1, 4)));
            add(new ArrayList<>(Arrays.asList(3, 6)));
            add(new ArrayList<>(Arrays.asList(8, 10)));
        }};
        String arrayJson = mapper.writeValueAsString(t);
        mockMvc.perform(post("/api/v1/intervals/merge?kind=digits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(arrayJson))
                .andExpect(status().isOk());
    }
    @Test
    void PostTest2() throws Exception {
        ArrayList<ArrayList<Integer>> t = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList(1, 4)));
            add(new ArrayList<>(Arrays.asList(3, -15)));
            add(new ArrayList<>(Arrays.asList(8, 10)));
        }};
        String arrayJson = mapper.writeValueAsString(t);
        mockMvc.perform(post("/api/v1/intervals/merge?kind=digits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(arrayJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    void PostTest3() throws Exception {
        ArrayList<ArrayList<Integer>> t = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList(1, 4)));
            add(new ArrayList<>(Arrays.asList(3, 3)));
            add(new ArrayList<>(Arrays.asList(8, 10)));
        }};
        String arrayJson = mapper.writeValueAsString(t);
        mockMvc.perform(post("/api/v1/intervals/merge?kind=digits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(arrayJson))
                .andExpect(status().isOk());
    }
    @Test
    void PostTest4() throws Exception {
        ArrayList<ArrayList<String>> t = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList("a", "f")));
            add(new ArrayList<>(Arrays.asList("d", "j")));
            add(new ArrayList<>(Arrays.asList("r", "z")));
        }};
        String arrayJson = mapper.writeValueAsString(t);
        mockMvc.perform(post("/api/v1/intervals/merge?kind=letters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(arrayJson))
                .andExpect(status().isOk());
    }
    @Test
    void PostTest5() throws Exception {
        ArrayList<ArrayList<String>> t = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList("a", "f")));
            add(new ArrayList<>(Arrays.asList("d", ".")));
            add(new ArrayList<>(Arrays.asList("r", "z")));
        }};
        String arrayJson = mapper.writeValueAsString(t);
        mockMvc.perform(post("/api/v1/intervals/merge?kind=letters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(arrayJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    void PostTest6() throws Exception {
        ArrayList<ArrayList<String>> t = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList("a", "f")));
            add(new ArrayList<>(Arrays.asList("d", "a")));
            add(new ArrayList<>(Arrays.asList("r", "z")));
        }};
        String arrayJson = mapper.writeValueAsString(t);
        mockMvc.perform(post("/api/v1/intervals/merge?kind=letters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(arrayJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    void PostTest7() throws Exception {
        ArrayList<ArrayList<String>> t = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList("A", "f")));
            add(new ArrayList<>(Arrays.asList("d", "Z")));
            add(new ArrayList<>(Arrays.asList("r", "Z")));
        }};
        String arrayJson = mapper.writeValueAsString(t);
        mockMvc.perform(post("/api/v1/intervals/merge?kind=letters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(arrayJson))
                .andExpect(status().isOk());
    }

}
