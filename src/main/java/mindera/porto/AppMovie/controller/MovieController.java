package mindera.porto.AppMovie.controller;

import mindera.porto.AppMovie.dto.movieDto.MovieCreateDto;
import mindera.porto.AppMovie.dto.movieDto.MovieReadDto;
import mindera.porto.AppMovie.dto.movieDto.MovieUpdateDto;
import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public MovieReadDto addNewMovie(@RequestBody MovieCreateDto movie) {
        MovieReadDto movieReadDto = this.movieService.addMovie(movie);

        return movieReadDto;
    }

    @PostMapping("")
    public List<MovieReadDto> addNewMovies(@RequestBody Set<MovieCreateDto> moviesCreateDto) {
        return movieService.addNewMovies(moviesCreateDto);
    }

    @GetMapping("")
    public List<MovieReadDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovieById(id);
    }

    @PostMapping("/{movieId}/{actorIds}")
    public MovieReadDto addActorsToMovie(@PathVariable Long movieId, @PathVariable String actorIds) {

        String[] actorIdStrings = actorIds.split(",");
        List<Long> actorIdList = new ArrayList<>();
        for (String actorIdString : actorIdStrings) {

            actorIdList.add(Long.parseLong(actorIdString.trim()));
        }
        return movieService.addActorsToMovie(movieId, actorIdList);
    }

    @GetMapping("/{movieId}")
    public MovieReadDto getMovieById(@PathVariable Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @PutMapping("/{movieId}")
    public MovieReadDto updateMovieById(@PathVariable Long movieId, @RequestBody MovieUpdateDto movieUpdateDto) {
        return movieService.updateMovieById(movieId, movieUpdateDto);
    }

    @GetMapping("/actor/{actorId}")
    public List<MovieReadDto> getMoviesByActorId(@PathVariable Long actorId) {
        return movieService.getMoviesByActorId(actorId);
    }

    @GetMapping("/search/byName")
    public List<MovieReadDto> searchMoviesByName(@RequestParam("name") String name) {
        return movieService.searchMoviesByName(name);
    }

    // Endpoint para pesquisar filmes por nome de ator
    @GetMapping("/search/byActor")
    public List<MovieReadDto> searchMoviesByActorName(@RequestParam("actorName") String actorName) {
        return movieService.searchMoviesByActorName(actorName);
    }

    @GetMapping("/search/byYears")
    public List<MovieReadDto> searchMoviesBetweenYears(
            @RequestParam("startYear") int startYear,
            @RequestParam("endYear") int endYear) {
        return movieService.searchMoviesBetweenYears(startYear, endYear);
    }

}
