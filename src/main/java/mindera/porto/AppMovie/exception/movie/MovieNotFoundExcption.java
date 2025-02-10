package mindera.porto.AppMovie.exception.movie;

public class MovieNotFoundExcption extends MovieException {
    public MovieNotFoundExcption(String message) {
        super("Movie not Found");
    }
}
