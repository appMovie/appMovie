package mindera.porto.AppMovie.exception.tvShow;

public class TvShowException extends RuntimeException {
    public TvShowException(String message) {
        super("TvShow Error: " + message);
    }
}
