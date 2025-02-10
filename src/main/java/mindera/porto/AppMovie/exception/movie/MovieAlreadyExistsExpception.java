package mindera.porto.AppMovie.exception.movie;

public class MovieAlreadyExistsExpception extends RuntimeException {
    public MovieAlreadyExistsExpception(String message) {
        super("Movie already exists");
    }
}
