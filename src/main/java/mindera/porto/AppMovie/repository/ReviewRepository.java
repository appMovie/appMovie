package mindera.porto.AppMovie.repository;

import mindera.porto.AppMovie.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);
    List<Review> findByMovieId(Long movieId);
    List<Review> findByTvShowId(Long tvShowId);

}
