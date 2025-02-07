package mindera.porto.AppMovie.exception.tvShow;

public class TvShowNotFoundDirectorException extends TvShowException {
    public TvShowNotFoundDirectorException(Long directorId) {
        super("No tvShow found with the director" + directorId);
    }
}
