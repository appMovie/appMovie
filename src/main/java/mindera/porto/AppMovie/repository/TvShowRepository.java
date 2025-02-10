package mindera.porto.AppMovie.repository;

import mindera.porto.AppMovie.model.Movie;
import mindera.porto.AppMovie.model.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {
    Optional <TvShow> findByName (String name);


    List<TvShow> getTvShowByName(String name);

    List<TvShow> findTvShowByYear(int year);
}







