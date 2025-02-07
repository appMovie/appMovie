package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.model.Actor;
import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.repository.ActorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository){
        this.actorRepository=actorRepository;
    }

    public List<Actor> addActors(List<Actor> actors) {
        return actorRepository.saveAll(actors);
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public void deleteActorById(Long id) {
        actorRepository.deleteById(id);
    }

    public List<Movie> getMoviesByActor(Long actorId) {
        Actor actor = actorRepository.findById(actorId)
                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ator não encontrado com o id: " + actorId));
        return actor.getMovies();
    }


}
