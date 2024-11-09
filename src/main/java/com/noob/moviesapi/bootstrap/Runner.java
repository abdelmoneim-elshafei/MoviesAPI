package com.noob.moviesapi.bootstrap;

import com.github.javafaker.Faker;
import com.noob.moviesapi.entities.Movie;
import com.noob.moviesapi.entities.auth.Authority;
import com.noob.moviesapi.entities.auth.Role;
import com.noob.moviesapi.repositories.MovieRepo;
import com.noob.moviesapi.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final MovieRepo movieRepo;
    private final RoleRepo roleRepo;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {
        addMovies();
        addRoles();
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
