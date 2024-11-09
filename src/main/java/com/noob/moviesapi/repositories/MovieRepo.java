package com.noob.moviesapi.repositories;

import com.noob.moviesapi.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
}
