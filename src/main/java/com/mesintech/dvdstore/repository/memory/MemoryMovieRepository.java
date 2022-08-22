package com.mesintech.dvdstore.repository.memory;

import com.mesintech.dvdstore.entity.Movie;
import com.mesintech.dvdstore.repository.IMovieRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class MemoryMovieRepository implements IMovieRepositoryInterface {

    private List<Movie> movies = new ArrayList<Movie>();

    private static long movieId = 0;

    @Override
    public Movie save(Movie movie) {
        movie.setId(++movieId);
        movies.add(movie);
        System.out.println("Le film "+ movie.getTitle() + " est un film appartenant à la catégorie " + movie.getGenre());
        System.out.println("Dans ce film, "+ movie.getDescription());
        return movie;
    }

    @Override
    public Iterable<Movie> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.of(movies.stream().
                filter(m -> m.getId()==id).
                findFirst().get());
    }

    @Override
    public <S extends Movie> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Movie> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Movie entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Movie> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
