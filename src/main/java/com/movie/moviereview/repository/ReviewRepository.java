package com.movie.moviereview.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.moviereview.entity.Movie;
import com.movie.moviereview.entity.Review;
import com.movie.moviereview.entity.User;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    
    //Finds all reviews associated with a specific movie.
    List<Review> findByMovie(Movie movie);

    //Finds a review by a specific user for a specific movie.
    Optional<Review> findByUserAndMovie(User user,Movie movie);

    //Calculates the average rating for a given movie.
    Optional<Double> findAverageRatingByMovie(Movie movie);

    //Counts the number of reviews with a rating (i.e., ratingValue is not null) for a given movie.
    long countByMovieAndRatingValueIsNotNull(Movie movie);
}
