package mindera.porto.AppMovie.repository;

import mindera.porto.AppMovie.model.Actor;
import mindera.porto.AppMovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByName(String name);
    Optional<Movie> findByName(String name);
    List<Movie> findByDirectorId(Long directorId);

    @Query("SELECT m FROM Movie m JOIN m.actors a WHERE a.id = :actorId")
    List<Movie> findMoviesByActorId(@Param("actorId") Long actorId);

    @Query("SELECT m FROM Movie m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Movie> searchMoviesByName(@Param("name") String name);

    @Query("SELECT m FROM Movie m JOIN m.actors a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :actorName, '%'))")
    List<Movie> searchMoviesByActorName(@Param("actorName") String actorName);

    @Query("SELECT m FROM Movie m WHERE m.year BETWEEN :startYear AND :endYear")
    List<Movie> findMoviesBetweenYears(@Param("startYear") int startYear, @Param("endYear") int endYear);
}
