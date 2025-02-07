package mindera.porto.AppMovie.exception.tvShow;

public class TvShowNotFoundReviewException extends TvShowException {
    public TvShowNotFoundReviewException(Long reviewId) {
        super("No tvShow found with the review" + reviewId);
    }
}
