package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.model.Actor;
import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.repository.ActorRepository;
import mindera.porto.AppMovie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;


    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

   /* public void addNewMovie(Movie movie) {

        movieRepository.save(movie);
    }*/

    public void addNewMovies(Set<Movie> movies) {

        for (Movie movie : movies) {
            //movie.setId(null);

            Set<Actor> updatedActors = new HashSet<>();

            for (Actor actor : movie.getActors()) {

                Optional<Actor> existingActor = actorRepository.findByName(actor.getName());

                if (existingActor.isPresent()) {
                    updatedActors.add(existingActor.get()); // Reutiliza o ator existente
                } else {
                    updatedActors.add(actor); // Adiciona um novo ator se não existir
                }
            }

            movie.setActors(updatedActors); 
        }

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

}
