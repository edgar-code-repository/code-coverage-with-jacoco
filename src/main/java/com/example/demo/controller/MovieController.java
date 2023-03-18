package com.example.demo.controller;


import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.response.MovieListResponseDTO;
import com.example.demo.dto.response.MovieResponseDTO;
import com.example.demo.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<MovieListResponseDTO> getMovieList() {
        log.debug("[getMovieList][START]");

        List<MovieDTO> movies = movieService.getMovieList();
        MovieListResponseDTO movieListResponse = null;
        if (movies == null) {
            log.debug("[getMovieList][movies list is null]");
            movieListResponse = MovieListResponseDTO.builder()
                    .message("List is empty!!!")
                    .build();
        }
        else {
            log.debug("[getMovieList][movies list size: " + movies.size() + "]");
            movieListResponse = MovieListResponseDTO.builder()
                    .message("List retrieved!!!")
                    .movieList(movies)
                    .build();
        }

        log.debug("[getMovieList][END]");
        return ResponseEntity.ok().body(movieListResponse);
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable int movieId) {
        log.debug("[getMovieById][START]");
        log.debug("[getMovieById][movieId: " + movieId + "]");

        MovieResponseDTO movieResponse = null;
        MovieDTO movie = movieService.getMovieById(movieId);
        if (movie == null) {
            movieResponse = MovieResponseDTO.builder()
                    .message("Movie was not found!!!")
                    .build();
        }
        else {
            movieResponse = MovieResponseDTO.builder()
                    .movie(movie)
                    .message("Movie retrieved!!!")
                    .build();
        }

        log.debug("[getMovieById][movie retrieved: " + movie.toString() + "]");
        log.debug("[getMovieById][END]");
        return ResponseEntity.ok().body(movieResponse);
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieDTO movieDTO) {
        log.debug("[createMovie][START]");
        log.debug("[createMovie][movieDTO parameter: " + movieDTO.toString() + "]");

        movieDTO = movieService.createMovie(movieDTO);
        MovieResponseDTO movieResponse = MovieResponseDTO.builder()
                .movie(movieDTO)
                .message("Movie was saved!!")
                .build();

        if (movieDTO != null) log.debug("[createMovie][movie saved: " + movieDTO.toString() + "]");
        log.debug("[createMovie][END]");
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }

}
