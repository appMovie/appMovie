package mindera.porto.AppMovie.repository;

import mindera.porto.AppMovie.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    boolean existsByName(String name);
    Optional<Actor> findByName(String name);
}
