package mindera.porto.AppMovie.controller;

import mindera.porto.AppMovie.model.Actor;
import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.service.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping("")
    public List<Actor> addActors(@RequestBody List<Actor> actors) {
        return actorService.addActors(actors);
    }

    @GetMapping("")
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Long id) {
        actorService.deleteActorById(id);
    }

    @GetMapping("/{actorId}/movies")
    public List<Movie> getMoviesByActor(@PathVariable Long actorId) {
        return actorService.getMoviesByActor(actorId);
    }

}
