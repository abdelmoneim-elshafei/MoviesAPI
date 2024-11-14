package com.noob.moviesapi.services;

import com.noob.moviesapi.dtos.MovieCSV;

import java.io.File;
import java.util.List;

public interface MovieCSVService {
    public List<MovieCSV> convertToMovieCSV(File csvFile);
}
