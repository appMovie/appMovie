package mindera.porto.AppMovie.repository;

import mindera.porto.AppMovie.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query("SELECT a FROM Actor a JOIN FETCH a.movies WHERE a.name = :actorName")
    Optional<Actor> findMoviesByActorName(@Param("actorName") String actorName);

    @Query("SELECT a FROM Actor a WHERE a.name=?1")
    Optional<Actor> findByName(String name);
}
