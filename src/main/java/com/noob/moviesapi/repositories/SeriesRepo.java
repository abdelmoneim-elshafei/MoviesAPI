package com.noob.moviesapi.repositories;

import com.noob.moviesapi.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepo extends JpaRepository<Series, Long> {
}
