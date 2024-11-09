package com.noob.moviesapi.services;

import com.noob.moviesapi.entities.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    public void addMovie(Movie movie);
    public Optional<Movie> getMovieById(Long id);
    public List<Movie> getMovies();
}
