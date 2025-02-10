package mindera.porto.AppMovie.exception.movie;

public class MovieNotFoundExcption extends RuntimeException {
    public MovieNotFoundExcption(String message) {
        super("Movie not Found");
    }
}
