package com.noob.moviesapi.repositories;

import com.noob.moviesapi.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface GenreRepo extends JpaRepository<Genre, Long> {
//    public Genre saveIFNotExists(Genre genre);
    public Optional<Genre> findByName(String name);
}
