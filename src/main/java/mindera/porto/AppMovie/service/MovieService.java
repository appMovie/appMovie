package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.dto.movieDto.MovieCreateDto;
import mindera.porto.AppMovie.dto.movieDto.MovieReadDto;
import mindera.porto.AppMovie.dto.movieDto.MovieUpdateDto;
import mindera.porto.AppMovie.exception.movie.MovieAlreadyExistsExpception;
import mindera.porto.AppMovie.exception.movie.MovieNotFoundExcption;
import mindera.porto.AppMovie.mapper.MovieMapper;
import mindera.porto.AppMovie.model.Actor;
import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.repository.ActorRepository;
import mindera.porto.AppMovie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository= actorRepository;
    }


    public MovieReadDto addMovie(MovieCreateDto movieCreateDto){

        try {
            Movie movie = MovieMapper.fromMovieCreateDtoToMovie(movieCreateDto);
            movie = movieRepository.save(movie);
            return MovieMapper.fromMovieToMovieReadDto(movie);
        } catch (DataIntegrityViolationException ex) {
            throw new MovieAlreadyExistsExpception("Movie already exists");
        }
    }


//    public List<MovieReadDto> addNewMovies(Set<MovieCreateDto> moviesCreateDto) {
//
//        Set<Movie> movies = moviesCreateDto.stream()
//                                    .map(MovieMapper::fromMovieCreateDtoToMovie)
//                                    .collect(Collectors.toSet());
//
//        List<Movie> savedMovies = movieRepository.saveAll(movies);
//
//        return savedMovies.stream()
//                       .map(MovieMapper::fromMovieToMovieReadDto)
//                       .collect(Collectors.toList());
//
//    }

    public List<MovieReadDto> addNewMovies(Set<MovieCreateDto> moviesCreateDto) {
        Set<Movie> moviesToSave = new HashSet<>();

        for (MovieCreateDto dto : moviesCreateDto) {
            // Verifica se já existe um filme com o mesmo nome
            if (movieRepository.existsByName(dto.getName())) {
                throw new MovieAlreadyExistsExpception("Movie already exists: " + dto.getName());
            }
            Movie movie = MovieMapper.fromMovieCreateDtoToMovie(dto);
            moviesToSave.add(movie);
        }

        List<Movie> savedMovies = movieRepository.saveAll(moviesToSave);
        return savedMovies.stream()
                       .map(MovieMapper::fromMovieToMovieReadDto)
                       .collect(Collectors.toList());
    }


    public List<MovieReadDto> getAllMovies() {

        return movieRepository.findAll()
                       .stream()
                       .map(MovieMapper::fromMovieToMovieReadDto)
                       .toList();
    }

    public void deleteMovieById(Long id) {

        movieRepository.deleteById(id);
    }

    public MovieReadDto addActorsToMovie(Long movieId, List<Long> actorIds) {

        Movie movie = movieRepository.findById(movieId)
                              .orElseThrow(() -> new MovieNotFoundExcption("Movie not found"));

        List<Actor> actors = actorRepository.findAllById(actorIds);

        movie.getActors().addAll(actors);

        movie = movieRepository.save(movie);

        return MovieMapper.fromMovieToMovieReadDto(movie);

    }

    public MovieReadDto getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                              .orElseThrow(() -> new MovieNotFoundExcption("Movie not found "+movieId));
        return MovieMapper.fromMovieToMovieReadDto(movie);
    }

//    public MovieReadDto updateMovieById(Long movieId, MovieUpdateDto movieUpdateDto) {
//
//
//        Movie movie = movieRepository.findById(movieId)
//                              .orElseThrow(() -> new MovieNotFoundExcption("Movie not found"));
//
//        movie.getActors().clear();
//
//        // Atualiza com os dados do DTO
//        MovieMapper.fromMovieUpdateDtoToMovie(movieUpdateDto, movie);
//
//        movie = movieRepository.save(movie);
//
//        return MovieMapper.fromMovieToMovieReadDto(movie);
//    }

    public MovieReadDto updateMovieById(Long movieId, MovieUpdateDto movieUpdateDto) {
        // Busca o filme a ser atualizado
        Movie existingMovie = movieRepository.findById(movieId)
                                      .orElseThrow(() -> new MovieNotFoundExcption("Movie not found"));

        // Verifica se já existe outro filme com o mesmo nome
        Optional<Movie> movieWithSameName = movieRepository.findByName(movieUpdateDto.getName());
        if (movieWithSameName.isPresent() && !movieWithSameName.get().getId().equals(movieId)) {
            throw new MovieAlreadyExistsExpception("Movie already exists: " + movieUpdateDto.getName());
        }

        // Se existir uma associação com atores, pode limpá-las conforme sua lógica
        existingMovie.getActors().clear();

        // Atualiza os campos do filme com os dados do DTO
        MovieMapper.fromMovieUpdateDtoToMovie(movieUpdateDto, existingMovie);

        // Salva a entidade atualizada
        Movie updatedMovie = movieRepository.save(existingMovie);
        return MovieMapper.fromMovieToMovieReadDto(updatedMovie);
    }


    public List<MovieReadDto> getMoviesByActorId(Long actorId) {
        List<Movie> movies = movieRepository.findMoviesByActorId(actorId);
        return movies.stream()
                       .map(MovieMapper::fromMovieToMovieReadDto)
                       .toList();
    }

    public List<MovieReadDto> searchMoviesByName(String name) {
        List<Movie> movies = movieRepository.searchMoviesByName(name);
        return movies.stream()
                       .map(MovieMapper::fromMovieToMovieReadDto)
                       .toList();
    }

    public List<MovieReadDto> searchMoviesByActorName(String actorName) {
        List<Movie> movies = movieRepository.searchMoviesByActorName(actorName);
        return movies.stream()
                       .map(MovieMapper::fromMovieToMovieReadDto)
                       .toList();
    }

    public List<MovieReadDto> searchMoviesBetweenYears(int startYear, int endYear) {
        List<Movie> movies = movieRepository.findMoviesBetweenYears(startYear, endYear);
        return movies.stream()
                       .map(MovieMapper::fromMovieToMovieReadDto)
                       .toList();
    }

}
