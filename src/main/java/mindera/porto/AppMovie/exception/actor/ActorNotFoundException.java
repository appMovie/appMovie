package mindera.porto.AppMovie.exception.actor;

public class ActorNotFoundException extends RuntimeException{
    public ActorNotFoundException(String message) {
        super("Actor not Found");
    }
}
