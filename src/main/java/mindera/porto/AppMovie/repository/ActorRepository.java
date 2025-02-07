package mindera.porto.AppMovie.repository;

import mindera.porto.AppMovie.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {


}
