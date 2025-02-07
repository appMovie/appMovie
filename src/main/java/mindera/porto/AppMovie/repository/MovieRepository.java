package mindera.porto.AppMovie.repository;

import mindera.porto.AppMovie.model.Actor;
import mindera.porto.AppMovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE LOWER(m.name) = LOWER(:name)")
    Optional<Movie> findByName(@Param("name") String name);

}
