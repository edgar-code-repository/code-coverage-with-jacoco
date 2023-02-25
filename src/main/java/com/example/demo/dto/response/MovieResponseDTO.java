package com.example.demo.dto.response;

import com.example.demo.dto.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {

    private String message;

    private MovieDTO movie;

}
