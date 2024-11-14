package com.noob.moviesapi.dtos;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieCSV {
    @CsvBindByName(column = "Title")
    private String title;
    @CsvBindByName(column = "URL")
    private String url;
    @CsvBindByName(column = "Release Date")
    private String releaseDate;
    @CsvBindByName(column = "IMDb Rating")
    private Float rating;
    @CsvBindByName(column = "Runtime (mins)")
    private Short runtime;
    @CsvBindByName(column = "Genres")
    private String genres;
    @CsvBindByName(column = "Directors")
    private String director;
}
