package com.noob.moviesapi.services;

import com.noob.moviesapi.dtos.MovieCSV;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
class MovieCSVServiceImplTest {
    MovieCSVService movieCSVService = new MovieCSVServiceImpl();

    @Test
    public void convertToMovieCSV() {
        Set<String> genres = new HashSet<>();
        List<MovieCSV> data = movieCSVService.convertToMovieCSV(new File("src/main/resources/movies.csv"));
        data.forEach(d -> log.info(d.toString()));
        assertThat(data.size()).isGreaterThan(10);

        for (MovieCSV d : data) {
           genres.addAll(Arrays.stream(d.getGenres().split(",")).map(String::trim).collect(Collectors.toSet()));
        }

        genres.forEach(log::info);

    }

}