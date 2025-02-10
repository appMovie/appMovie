package mindera.porto.AppMovie.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    // Violação de integridade de dados (chaves duplicadas, restrições, etc.)
   @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e){
       if (e.getMessage().contains("Duplicated key")){
           return new ResponseEntity<>("Resource already exists", HttpStatus.CONFLICT);
       }
       return new ResponseEntity<>("Data integrity violation", HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
       return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
   }

    // Recurso não encontrado (exemplo: entidade não encontrada)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Corpo da requisição mal formatado (exemplo: JSON inválido)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>("Malformed JSON request", HttpStatus.BAD_REQUEST);
    }

    //Erro 500 ao criar tvShow
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception e) {
        e.printStackTrace();
        String message = "Something went wrong, please try again later.";
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }







}
