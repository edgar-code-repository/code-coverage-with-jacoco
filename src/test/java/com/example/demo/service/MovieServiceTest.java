package com.example.demo.service;

import com.example.demo.dto.MovieDTO;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void getMovieListTest() {
        when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Name X", 1980),
                        new Movie(2, "Name Y", 1985)
                )
        );

        movieService.setModelMapper(modelMapper);
        List<MovieDTO> movieList = movieService.getMovieList();

        Assertions.assertTrue(movieList != null);
        Assertions.assertTrue(movieList.size() > 0);
        Assertions.assertEquals(1, movieList.get(0).getMovieId());
        Assertions.assertEquals(2, movieList.get(1).getMovieId());

    }

    @Test
    public void getMovieByIdTest() {
        when(movieRepository.findById(3)).thenReturn(Optional.of(new Movie(3, "Name Z", 1979)));

        movieService.setModelMapper(modelMapper);
        MovieDTO movieById = movieService.getMovieById(3);

        Assertions.assertTrue(movieById != null);
        Assertions.assertEquals(3, movieById.getMovieId());

    }

    @Test
    public void getMovieByIdNull() {
        when(movieRepository.findById(3)).thenReturn(Optional.empty());

        movieService.setModelMapper(modelMapper);
        MovieDTO movieById = movieService.getMovieById(3);

        Assertions.assertTrue(movieById == null);

    }

    @Test
    public void createMovieTest() {
        when(movieRepository.save(any(Movie.class))).thenReturn(new Movie(4, "Name TTT", 1982));

        movieService.setModelMapper(modelMapper);

        MovieDTO createdMovieDTO = movieService.createMovie(new MovieDTO());

        Assertions.assertTrue(createdMovieDTO != null);
        Assertions.assertEquals(4, createdMovieDTO.getMovieId());
    }


}
