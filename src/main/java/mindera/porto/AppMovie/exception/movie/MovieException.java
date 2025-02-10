package mindera.porto.AppMovie.exception.movie;

public class MovieException extends RuntimeException {
    public MovieException(String message) {
        super("Movie error : "+message);
    }
}
