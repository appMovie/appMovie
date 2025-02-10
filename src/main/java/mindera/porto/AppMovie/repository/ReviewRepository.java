package mindera.porto.AppMovie.repository;

@Repository

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);
    List<Review> findByMovieId(Long movieId);
    List<Review> findByTvShowId(Long tvShowId);

}
