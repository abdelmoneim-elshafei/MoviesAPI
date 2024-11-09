package com.noob.moviesapi.repositories;

import com.noob.moviesapi.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Long> {
}
