package com.movie.moviereview.service;

import java.util.List;

import com.movie.moviereview.dto.MovieDto;

public interface MovieService {
    
    MovieDto createMovie(MovieDto movieDto);

    List<MovieDto> getAllMovies();

    MovieDto getMovieById(Long id);

    MovieDto updateMovie(Long id,MovieDto movieDto);

    void deleteMovie(Long id);
}
