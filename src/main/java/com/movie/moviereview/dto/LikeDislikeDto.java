package com.movie.moviereview.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeDislikeDto {
    @NotNull(message = "isLiked cannot be null")
    private Boolean isLiked; // true for like, false for dislike
}
