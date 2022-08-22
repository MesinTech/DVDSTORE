package com.mesintech.dvdstore.service;

import com.mesintech.dvdstore.entity.Movie;
import com.mesintech.dvdstore.repository.IMovieRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultMovieService implements IMovieServiceInterface {

    public IMovieRepositoryInterface getMovieRepository() {
        return movieRepository;
    }

    @Autowired
    public void setMovieRepository(IMovieRepositoryInterface movieRepository) {
        this.movieRepository = movieRepository;
    }

    private IMovieRepositoryInterface movieRepository;

    public Movie registerMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Iterable<Movie> getMovieList() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(long id) {
        return movieRepository.findById(id).orElseThrow();
    }
}
