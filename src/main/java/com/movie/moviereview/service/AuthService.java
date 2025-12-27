package com.movie.moviereview.service;

import com.movie.moviereview.dto.JwtAuthResponse;
import com.movie.moviereview.dto.LoginDto;
import com.movie.moviereview.dto.RegisterDto;

public interface AuthService {
    
    //Registers a new user
    String register(RegisterDto registerDto);

    //Authenticates a user and generates a JWT token upon successful login.
    JwtAuthResponse login(LoginDto loginDto);
}
