package mindera.porto.AppMovie.exception.actor;

public class ActorAlreadyExistsException extends RuntimeException {
    public ActorAlreadyExistsException(String message) {
        super("Actor already exists");
    }
}
