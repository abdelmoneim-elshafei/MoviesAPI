package com.noob.moviesapi.repositories;

import com.noob.moviesapi.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepo extends JpaRepository<Director, Long> {
}
