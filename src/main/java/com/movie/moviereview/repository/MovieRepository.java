package com.movie.moviereview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.moviereview.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    
}
