package com.noob.moviesapi.bootstrap;

import com.github.javafaker.Faker;
import com.noob.moviesapi.dtos.MovieCSV;
import com.noob.moviesapi.entities.Director;
import com.noob.moviesapi.entities.Genre;
import com.noob.moviesapi.entities.Movie;
import com.noob.moviesapi.entities.auth.Authority;
import com.noob.moviesapi.entities.auth.Role;
import com.noob.moviesapi.repositories.DirectorRepo;
import com.noob.moviesapi.repositories.GenreRepo;
import com.noob.moviesapi.repositories.MovieRepo;
import com.noob.moviesapi.repositories.RoleRepo;
import com.noob.moviesapi.services.MovieCSVService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final MovieRepo movieRepo;
    private final RoleRepo roleRepo;
    private final Faker faker;
    private final MovieCSVService movieCSVService;
    private final GenreRepo genreRepo;
    private final DirectorRepo directorRepo;

    @Override
    public void run(String... args) throws Exception {
        if (movieRepo.count() < 10) {
            addMovies();
            addRoles();
            loadCSV();
        }
    }

    private void loadCSV() {
        List<MovieCSV> movieCSVS = movieCSVService
                .convertToMovieCSV(new File("src/main/resources/movies.csv"));
        addGenres(movieCSVS);
        addDirectors(movieCSVS);
        for (MovieCSV movieCSV : movieCSVS) {
            if (movieRepo.findByTitle(movieCSV.getTitle()).isEmpty()) {
                Set<Genre> genres = new HashSet<>();
                for (String genre : Arrays.stream(movieCSV.getGenres().split(",")).map(String::trim).collect(Collectors.toSet())) {
                    genres.add(genreRepo.findByName(genre).get());
                }
                movieRepo.save(Movie.builder()
                        .title(movieCSV.getTitle())
                        .releaseDate(LocalDate.parse(movieCSV.getReleaseDate()))
                        .rating(movieCSV.getRating())
                        .runtime(movieCSV.getRuntime())
                        .url(movieCSV.getUrl())
                        .genres(genres)
                        .director(directorRepo.findByName(movieCSV.getDirector()).get())
                        .build());
            }
        }

    }

    private void addDirectors(List<MovieCSV> movieCSVS) {
        Set<Director> directors = new HashSet<>();
        for (MovieCSV movieCSV : movieCSVS) {
            directors.add(Director.builder().name(movieCSV.getDirector()).build());
        }

        for (Director director : directors) {
            if (directorRepo.findByName(director.getName()).isEmpty()) {
                directorRepo.save(director);
            }
//        directorRepo.saveAll(directors);
        }
    }

    private void addGenres(List<MovieCSV> data) {
            Set<String> genres = new HashSet<>();
            for (MovieCSV d : data) {
                genres.addAll(Arrays.stream(d.getGenres().split(",")).map(String::trim).collect(Collectors.toSet()));
            }
            for (String genre : genres) {
                if (genreRepo.findByName(genre).isEmpty()) {
                    genreRepo.save(Genre.builder().name(genre).build());
                }
            }

        }

    private void addRoles() {
        if (roleRepo.count() <= 0) {
            roleRepo.save(Role.builder().authority(Authority.ROLE_ADMIN).build());
            roleRepo.save(Role.builder().authority(Authority.ROLE_USER).build());
        }
    }

    private void addMovies() {
        if (movieRepo.count() < 5) {
            for (int i = 0; i < 10; i++) {
                movieRepo.save(Movie.builder()
                        .title(faker.book().title())
                        .releaseDate(LocalDate.of((2000 + ((i + 2) * 2)), (5 * (i + 1)) % 12, (10 * (i + 31)) % 29))
                        .rating(randomRating())
                        .build());
            }
        }
    }

    private float randomRating() {
        float min = 3.0f;
        float max = 9.5f;

        Random random = new Random();
        float randomFloat = min + random.nextFloat() * (max - min);
        return Math.round(randomFloat * 10) / 10.0f;
    }
}
