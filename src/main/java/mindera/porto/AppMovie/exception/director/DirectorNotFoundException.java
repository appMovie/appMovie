package mindera.porto.AppMovie.exception.director;

public class DirectorNotFoundException extends DirectorException {
    public DirectorNotFoundException() {
        super("Director not found");
    }
}
