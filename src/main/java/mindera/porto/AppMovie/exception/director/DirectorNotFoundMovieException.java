package mindera.porto.AppMovie.exception.director;

public class DirectorNotFoundMovieException extends DirectorException {
    public DirectorNotFoundMovieException(String movie) {
        super("No directors found for Movie" + movie);
    }
}
