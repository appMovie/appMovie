package mindera.porto.AppMovie.exception.movie;

public class MovieNotFoundActorException extends RuntimeException {
    public MovieNotFoundActorException(Long actorId) {
        super("No Movie found with the actor " +actorId);
    }
}
