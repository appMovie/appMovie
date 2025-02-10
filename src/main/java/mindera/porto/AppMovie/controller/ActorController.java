package mindera.porto.AppMovie.controller;

import mindera.porto.AppMovie.dto.actorDto.ActorCreateDto;
import mindera.porto.AppMovie.dto.actorDto.ActorReadDto;
import mindera.porto.AppMovie.dto.actorDto.ActorUpdateDto;
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
    public ActorReadDto addActor(@RequestBody ActorCreateDto actorCreateDto) {
        return actorService.addActor(actorCreateDto);
    }

    @PostMapping("")
    public List<ActorReadDto> addActors(@RequestBody List<ActorCreateDto> actors) {
        return actorService.addActors(actors);
    }

    @GetMapping("")
    public List<ActorReadDto> getAllActors() {
        return actorService.getAllActors();
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Long id) {
        actorService.deleteActorById(id);
    }

    @PutMapping("/{actorId}")
    public ActorReadDto updateActorById(@PathVariable Long actorId, @RequestBody ActorUpdateDto actorUpdateDto) {
        return actorService.updateActorById(actorId, actorUpdateDto);
    }

    @GetMapping("/{actorId}")
    public ActorReadDto getActorById(@PathVariable Long actorId) {
        return actorService.getActorById(actorId);
    }

}
