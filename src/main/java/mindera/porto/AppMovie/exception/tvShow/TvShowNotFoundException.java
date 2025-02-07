package mindera.porto.AppMovie.exception.tvShow;

import mindera.porto.AppMovie.exception.director.DirectorException;

public class TvShowNotFoundException extends TvShowException {
    public TvShowNotFoundException() {
        super("TvShow not found");
    }
}
