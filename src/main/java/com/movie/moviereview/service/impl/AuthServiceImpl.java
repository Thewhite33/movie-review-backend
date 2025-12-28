package com.movie.moviereview.service.impl;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.moviereview.dto.JwtAuthResponse;
import com.movie.moviereview.dto.LoginDto;
import com.movie.moviereview.dto.RegisterDto;
import com.movie.moviereview.entity.Role;
import com.movie.moviereview.entity.User;
import com.movie.moviereview.exceptions.MovieApiException;
import com.movie.moviereview.repository.RoleRepository;
import com.movie.moviereview.repository.UserRepository;
import com.movie.moviereview.service.AuthService;
import com.movie.moviereview.util.JwtTokenProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    
    @Override
    public String register(RegisterDto registerDto){
        
        //Check if username already exists
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new MovieApiException(HttpStatus.BAD_REQUEST, "User already exists by the username. Please use a different one !");
        }

        //Check if email already exits
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new MovieApiException(HttpStatus.BAD_REQUEST, "Email already registered. Please use a different one !");
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
                        .orElseThrow(() -> new MovieApiException(HttpStatus.INTERNAL_SERVER_ERROR, "User role not set"));
        
        roles.add(userRole);
        user.setRoles(roles);   
        
        userRepository.save(user);
        return "User created successfully";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail())
                    .orElseThrow(() -> new MovieApiException(HttpStatus.NOT_FOUND,"Invalid email or password"));

        String role = user.getRoles().stream().findFirst().map(Role::getName).orElse("ROLE_USER");
        
        return new JwtAuthResponse(token,"Bearer",user.getUsername(),role);
    }
}
