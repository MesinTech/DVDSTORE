package com.mesintech.dvdstore.service;

import com.mesintech.dvdstore.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface IMovieServiceInterface {
    Movie registerMovie(Movie movie);
    Iterable<Movie> getMovieList();

    Movie getMovieById(long id);
}
