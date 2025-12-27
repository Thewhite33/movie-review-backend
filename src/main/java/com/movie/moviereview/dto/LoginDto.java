package com.movie.moviereview.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    
    @NotBlank(message = "Must provide username or email")
    private String usernameOrEmail;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
