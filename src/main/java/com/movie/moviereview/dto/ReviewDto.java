package com.movie.moviereview.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReviewDto {
    private Long id;
    private Long movieId;
    private Long userId;
    private String username;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Integer ratingValue;

    @Size(max = 1000, message = "Comment cannot exceed 1000 characters")
    private String commentText;

    private LocalDateTime createdAt;
}
