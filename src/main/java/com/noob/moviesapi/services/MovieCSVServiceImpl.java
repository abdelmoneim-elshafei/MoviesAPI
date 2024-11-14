package com.noob.moviesapi.services;

import com.noob.moviesapi.dtos.MovieCSV;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class MovieCSVServiceImpl implements MovieCSVService {

    @Override
    public List<MovieCSV> convertToMovieCSV(File csvFile) {
        try {

            return new CsvToBeanBuilder<MovieCSV>(new FileReader(csvFile))
                    .withType(MovieCSV.class)
                    .build().parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
