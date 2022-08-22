package com.mesintech.dvdstore.repository.file;

import com.mesintech.dvdstore.entity.Movie;
import com.mesintech.dvdstore.repository.IMovieRepositoryInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

//@Repository
public class FileMovieRepository implements IMovieRepositoryInterface {

    private List<Movie> movies = new ArrayList<Movie>();
    @Value("${movies.file.location}")
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public Movie save(Movie movie) {
        FileWriter writer;
        long lastId= StreamSupport.stream(findAll().spliterator(), false).map(Movie::getId).max(Long::compare).orElse(0L);
        movie.setId(lastId+1);
        try{
            writer=new FileWriter(file,true);
            writer.write(movie.getId() + ";");
            writer.write(movie.getTitle() + ";");
            writer.write(movie.getGenre() + ";");
            writer.write(movie.getDescription() + "\n");
            System.out.println("Le film "+ movie.getTitle() + " est un film appartenant à la catégorie " + movie.getGenre());
            System.out.println("Dans ce film, "+ movie.getDescription());
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public Iterable<Movie> findAll() {

        List<Movie> movies=new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                final Movie movie=new Movie();
                final String[] allProperties = line.split("\\;");
                movie.setId(Long.parseLong(allProperties[0]));
                movie.setTitle(allProperties[1]);
                movie.setGenre(allProperties[2]);
                movie.setDescription(allProperties[3]);
                movies.add(movie);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public Optional<Movie> findById(Long id) {
        final Movie movie = new Movie();
        movie.setId(id);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {

                final String[] allProperties = line.split("\\;");
                final long nextMovieId=Long.parseLong(allProperties[0]);
                if (nextMovieId==id) {
                    movie.setTitle(allProperties[1]);
                    movie.setGenre(allProperties[2]);
                    movie.setDescription(allProperties[3]);
                    return Optional.of(movie);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("A movie from the file does not have a proper id");
            e.printStackTrace();
        }
        movie.setTitle("UNKNOWN");
        movie.setGenre("UNKNOWN");
        movie.setDescription("UNKNOWN");
        return Optional.of(movie);
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
