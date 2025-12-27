package com.movie.moviereview.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"title"})
})
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false,unique = true)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "genre",length = 100)
    private String genre;

    @Column(name = "director",length = 100)
    private String director;

    @Column(name = "poster_url",length = 500)
    private String posterUrl;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>(); 

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<LikeDislike> likeDislikes = new HashSet<>();
}
