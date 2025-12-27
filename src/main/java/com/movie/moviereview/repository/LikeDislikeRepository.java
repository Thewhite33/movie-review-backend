package com.movie.moviereview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movie.moviereview.entity.LikeDislike;
import com.movie.moviereview.entity.Movie;
import com.movie.moviereview.entity.User;

public interface LikeDislikeRepository extends JpaRepository<LikeDislike,Long> {
    
    //Finds a LikeDislike entry by user and movie.
    Optional<LikeDislike> findByUserAndMovie(User user,Movie movie);

    //Counts the number of likes for a specific movie.
    long countByMovieAndIsLiked(Movie movie,Boolean isLiked);

    //Deletes a LikeDislike entry by user and movie.
    void deleteByUserAndMovie(User user,Movie movie);

    /**
     * Custom query to get the like and dislike counts for a given movie.
     * Returns an array where index 0 is likeCount and index 1 is dislikeCount.
     * @param movieId The ID of the movie.
     * @return An array of Longs: [likeCount, dislikeCount]
     */
    @Query("SELECT " +
            "SUM(CASE WHEN ld.isLiked = TRUE THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN ld.isLiked = FALSE THEN 1 ELSE 0 END) " +
            "FROM LikeDislike ld WHERE ld.movie.id = :movieId")
    Long[] getLikeDislikeCountsByMovieId(@Param("movieId") Long movieId);
}
