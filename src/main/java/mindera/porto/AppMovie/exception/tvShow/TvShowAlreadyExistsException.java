package mindera.porto.AppMovie.exception.tvShow;

import mindera.porto.AppMovie.exception.director.DirectorException;

public class TvShowAlreadyExistsException extends TvShowException {
    public TvShowAlreadyExistsException() {
        super("TvShow already exists");
    }
}
