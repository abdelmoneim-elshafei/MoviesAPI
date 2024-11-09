package com.noob.moviesapi.services;

import com.noob.moviesapi.entities.Movie;
import com.noob.moviesapi.repositories.MovieRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MovieServiceImpl implements MovieService {
    private final MovieRepo movieRepo;
    @Override
    public void addMovie(Movie movie) {
        movieRepo.save(movie);
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepo.findById(id);
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepo.findAll();
    }
}
