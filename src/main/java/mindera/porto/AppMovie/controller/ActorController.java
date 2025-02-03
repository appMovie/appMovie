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


    @PostMapping("/add")
    public List<Actor> addActors(@RequestBody List<Actor> actors) {
        return actorService.addActors(actors);
    }

    @GetMapping("/{actorName}/movies")
    public List<Movie> getMoviesByActorName(@PathVariable String actorName) {
        return actorService.getMoviesByActorName(actorName);
    }
}
