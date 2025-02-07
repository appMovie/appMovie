package mindera.porto.AppMovie.controller;

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

    /*@PostMapping("/add")
    public void addNewMovie(@RequestBody Movie movie) {
        movieService.addNewMovie(movie);
    }*/

    @PostMapping("")
    public void addNewMovies(@RequestBody Set<Movie> movies) {
        movieService.addNewMovies(movies);
    }


    @GetMapping("/list")
    public List<Movie> getMovies() {

        return movieService.getMovies();
    }

    @GetMapping("/id/{id}")
    public List<Movie> getMovieById(@PathVariable List<Long> id) {

        return movieService.getMovieById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable Long id) {

        movieService.deleteMovieById(id);
    }

    @PutMapping("/{movieId}")
    public void updateMovie(@PathVariable Long movieId, @RequestBody Movie updatedMovie) {

        movieService.updateMovie(movieId, updatedMovie);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<?> getMovieByName(@PathVariable String name) {
        Optional<Movie> movie = movieService.findMovieByName(name);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                           .body("Movie not found");
        }
    }

    @PostMapping("/{movieId}/{actorIds}")
    public Movie addActorsToMovie(@PathVariable Long movieId, @PathVariable String actorIds) {

        String[] actorIdStrings = actorIds.split(",");
        List<Long> actorIdList = new ArrayList<>();
        for (String actorIdString : actorIdStrings) {

            actorIdList.add(Long.parseLong(actorIdString.trim()));
        }
        return movieService.addActorsToMovie(movieId, actorIdList);
    }

}
