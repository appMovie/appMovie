package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.model.Actor;
import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.repository.ActorRepository;
import mindera.porto.AppMovie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;


    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository=actorRepository;
    }

   /* public void addNewMovie(Movie movie) {

        movieRepository.save(movie);
    }*/

    public void addNewMovies(Set<Movie> movies) {

        movieRepository.saveAll(movies);
    }


    public List<Movie> getMovies() {

        return movieRepository.findAll();
    }

    public List<Movie> getMovieById(List<Long> id) {
        List<Movie> movies = movieRepository.findAllById(id);
        if (movies.isEmpty()) {
            throw new RuntimeException("Movies with the provided IDs do not exist");
        }
        return movies;
    }

    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    public void updateMovie(Long id, Movie movieDetails) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.getActors().clear();

        movie.setName(movieDetails.getName());
        movie.setYear(movieDetails.getYear());
        movie.setDescription(movieDetails.getDescription());
        movie.setImageUrl(movieDetails.getImageUrl());
        // movie.setDirector(movieDetails.getDirector());
        // movie.setActors(movieDetails.getActors());
        // movie.setReviews(movieDetails.getReviews());
        movieRepository.save(movie);
    }

    public Optional<Movie> findMovieByName(String name) {
        return movieRepository.findByName(name);
    }

    public Movie addActorsToMovie(Long movieId, List<Long> actorIds) {

        Movie movie = movieRepository.findById(movieId)
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado com o id: " + movieId));

        List<Actor> actors = actorRepository.findAllById(actorIds);

        movie.getActors().addAll(actors);

        return movieRepository.save(movie);

    }

}
