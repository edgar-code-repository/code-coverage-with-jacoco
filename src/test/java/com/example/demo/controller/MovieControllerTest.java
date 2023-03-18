package com.example.demo.controller;


import com.example.demo.dto.MovieDTO;
import com.example.demo.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@Slf4j
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    public void getMoviesListTest() throws Exception {
        when(movieService.getMovieList()).thenReturn(
                Arrays.asList(
                        new MovieDTO(1, "Name X", 1980),
                        new MovieDTO(2, "Name Y", 1985)
                )
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/movies")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{'message':'List retrieved!!!','movieList':[{'movieId':1,'name':'Name X','yearRelease':1980},{'movieId':2,'name':'Name Y','yearRelease':1985}]}"))
                .andReturn();

        log.debug("mvc result getMoviesListTest: " + mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void getMoviesListNullTest() throws Exception {
        when(movieService.getMovieList()).thenReturn(null);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/movies")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{'message':'List is empty!!!'}"))
                .andReturn();

        log.debug("mvc result getMoviesListNullTest: " + mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void getMovieByIdTest() throws Exception {
        when(movieService.getMovieById(1)).thenReturn(new MovieDTO(1, "Name X", 1980));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/movies/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{'message':'Movie retrieved!!!','movie':{'movieId':1,'name':'Name X','yearRelease':1980}}"))
                .andReturn();

        log.debug("mvc result getMovieByIdTest: " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void createMovieTest() throws Exception {
        when(movieService.createMovie(any(MovieDTO.class))).thenReturn(new MovieDTO(1, "Name X", 1980));

        ObjectMapper objectMapper = new ObjectMapper();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new MovieDTO()));

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json("{'message':'Movie was saved!!','movie':{'movieId':1,'name':'Name X','yearRelease':1980}}"))
                .andReturn();

        log.debug("mvc result createMovieTest: " + mvcResult.getResponse().getContentAsString());
    }

}
