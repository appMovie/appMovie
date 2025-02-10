package mindera.porto.AppMovie.exception.movie;

public class MovieNotFoundActorException extends MovieException {
    public MovieNotFoundActorException(Long actorId) {
        super("No Movie found with the actor " +actorId);
    }
}
