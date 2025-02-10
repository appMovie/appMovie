package mindera.porto.AppMovie.exception.movie;

public class MovieAlreadyExistsExpception extends MovieException {
    public MovieAlreadyExistsExpception(String message) {
        super("Movie already exists");
    }
}
