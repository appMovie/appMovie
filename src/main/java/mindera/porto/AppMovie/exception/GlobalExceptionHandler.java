package mindera.porto.AppMovie.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Hidden
public class GlobalExceptionHandler {

   @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e){
       if (e.getMessage().contains("duplicate key")){
           return new ResponseEntity<>("Resource already exists", HttpStatus.CONFLICT);
       }
       return new ResponseEntity<>("Data integrity violation", HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
       return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
   }

}
