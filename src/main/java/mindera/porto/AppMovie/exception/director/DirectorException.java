package mindera.porto.AppMovie.exception.director;

public class DirectorException extends RuntimeException {
    public DirectorException(String message) {
        super("Director Error: " + message);
    }
}
