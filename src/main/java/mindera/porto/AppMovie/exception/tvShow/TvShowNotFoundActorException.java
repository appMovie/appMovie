package mindera.porto.AppMovie.exception.tvShow;

import mindera.porto.AppMovie.exception.director.DirectorException;

public class TvShowNotFoundActorException extends TvShowException {
    public TvShowNotFoundActorException(Long actorId) {
        super("No tvShow found with the actor" + actorId);
    }
}
