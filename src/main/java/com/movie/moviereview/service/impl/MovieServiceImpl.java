package com.movie.moviereview.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.movie.moviereview.dto.MovieDto;
import com.movie.moviereview.entity.Movie;
import com.movie.moviereview.repository.LikeDislikeRepository;
import com.movie.moviereview.repository.MovieRepository;
import com.movie.moviereview.repository.ReviewRepository;
import com.movie.moviereview.service.MovieService;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final LikeDislikeRepository likeDislikeRepository;

    private Movie mapToEntity(MovieDto movieDto){
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setGenre(movieDto.getGenre());
        movie.setDirector(movieDto.getDirector());
        movie.setPosterUrl(movieDto.getPosterUrl());
        return movie;
    }

    private MovieDto mapToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        movieDto.setReleaseDate(movie.getReleaseDate());
        movieDto.setGenre(movie.getGenre());
        movieDto.setDirector(movie.getDirector());
        movieDto.setPosterUrl(movie.getPosterUrl());
        return movieDto;
    }

    private MovieDto mapToDtoWithStats(Movie movie) {
        MovieDto movieDto = mapToDto(movie);

        // Calculate average rating
        Optional<Double> avgRating = reviewRepository.findAverageRatingByMovie(movie);
        movieDto.setAverageRating(avgRating.orElse(0.0));

        // Count reviews
        movieDto.setReviewCount(reviewRepository.countByMovieAndRatingValueIsNotNull(movie));

        // Get like and dislike counts
        Long[] counts = likeDislikeRepository.getLikeDislikeCountsByMovieId(movie.getId());
        movieDto.setLikeCount(counts != null && counts[0] != null ? counts[0] : 0L);
        movieDto.setDislikeCount(counts != null && counts[1] != null ? counts[1] : 0L);

        return movieDto;
    }
    
    @Override
    @Transactional
    public MovieDto createMovie(MovieDto movieDto){
        Movie movie = mapToEntity(movieDto);
        Movie newMovie = movieRepository.save(movie);
        return mapToDto(newMovie);
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                    .map(this::mapToDtoWithStats)
                    .collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(Long id) {
        return null;
    }

    @Override
    public MovieDto updateMovie(Long id, MovieDto movieDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMovie'");
    }

    @Override
    public void deleteMovie(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMovie'");
    }

    
}
