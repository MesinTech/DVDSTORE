package com.mesintech.dvdstore.repository;

import com.mesintech.dvdstore.entity.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMovieRepositoryInterface extends CrudRepository<Movie,Long> {

}
