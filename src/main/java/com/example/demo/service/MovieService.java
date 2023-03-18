package com.example.demo.service;


import com.example.demo.dto.MovieDTO;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    @Setter
    private ModelMapper modelMapper;

    public List<MovieDTO> getMovieList() {
        log.debug("[getMovieList][START]");

        List<MovieDTO> movieList = null;
        List<Movie> moviesFromDB = movieRepository.findAll();

        movieList = moviesFromDB.stream()
                .map(m -> modelMapper.map(m, MovieDTO.class))
                .collect(Collectors.toList());

        return movieList;
    }

    public MovieDTO getMovieById(Integer id) {
        log.debug("[getMovieById][START][id: " + id + "]");

        Optional<Movie> movieOptional = movieRepository.findById(id);
        MovieDTO movie = movieOptional.isPresent() ? modelMapper.map(movieOptional.get(), MovieDTO.class) : null;
        return movie;
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {
        log.debug("[getMovieList][START][movieDTO: " + movieDTO.toString() + "]");

        Movie savedMovie = movieRepository.save(modelMapper.map(movieDTO, Movie.class));
        return modelMapper.map(savedMovie, MovieDTO.class);
    }

}
