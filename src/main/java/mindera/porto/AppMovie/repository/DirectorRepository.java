package mindera.porto.AppMovie.repository;

import mindera.porto.AppMovie.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    boolean existsByName(String name);
    Optional<Director> findByName (String name);
    List<Director> findByTvShows_Name(String tvShow);
    List<Director> findByMovies_Name(String movie);
}
