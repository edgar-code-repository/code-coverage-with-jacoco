package com.example.demo.service;


import com.example.demo.dto.MovieDTO;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MovieDTO> getMovieList() {
        List<MovieDTO> movieList = null;
        List<Movie> moviesFromDB = movieRepository.findAll();

        if (moviesFromDB != null && !moviesFromDB.isEmpty())
            movieList = moviesFromDB.stream()
                    .map(m -> modelMapper.map(m, MovieDTO.class))
                    .collect(Collectors.toList());

        return movieList;
    }

    public MovieDTO getMovieById(Integer id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        MovieDTO movie = movieOptional.isPresent() ? modelMapper.map(movieOptional.get(), MovieDTO.class) : null;
        return movie;
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie savedMovie = movieRepository.save(modelMapper.map(movieDTO, Movie.class));
        return modelMapper.map(savedMovie, MovieDTO.class);
    }

}
