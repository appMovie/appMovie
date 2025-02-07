package mindera.porto.AppMovie.exception.director;

public class DirectorNotFoundTvShowException extends DirectorException {
    public DirectorNotFoundTvShowException(String tvShow) {
        super("No directors found for TvShow" + tvShow);
    }
}
