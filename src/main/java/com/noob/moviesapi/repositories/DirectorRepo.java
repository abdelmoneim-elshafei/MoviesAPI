package com.noob.moviesapi.repositories;

import com.noob.moviesapi.entities.Director;
import com.noob.moviesapi.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepo extends JpaRepository<Director, Long> {
//    public Director saveIfNotExist(Director director);

    public Optional<Director> findByName(String director);
}
