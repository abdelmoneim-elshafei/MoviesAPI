package com.noob.moviesapi.repositories;

import com.noob.moviesapi.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
}
