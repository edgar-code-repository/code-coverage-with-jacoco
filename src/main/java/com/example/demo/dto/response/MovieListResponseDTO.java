package com.example.demo.dto.response;

import com.example.demo.dto.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MovieListResponseDTO {

    private String message;

    private List<MovieDTO> movieList;

}
