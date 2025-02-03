package mindera.porto.AppMovie.service;

import mindera.porto.AppMovie.model.Actor;
import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.repository.ActorRepository;
import org.springframework.stereotype.Service;

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

    public List<Movie> getMoviesByActorName(String actorName) {
        Actor actor = actorRepository.findMoviesByActorName(actorName)
                              .orElseThrow(() -> new RuntimeException("Actor with name " + actorName + " not found"));

        return actor.getMovies();
    }

}
