package mindera.porto.AppMovie.exception.director;

public class DirectorAlreadyExistsException extends DirectorException {
    public DirectorAlreadyExistsException() {
        super("Director already exists");
    }
}
