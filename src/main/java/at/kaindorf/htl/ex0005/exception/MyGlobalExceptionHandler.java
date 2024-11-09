package at.kaindorf.htl.ex0005.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {StudentNotFoundException.class})
    public ResponseEntity<ErrorMessage> handNotFound(StudentNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.NOT_FOUND.value());
        message.setTimestamp(LocalDateTime.now());
        message.setDescription(request.getDescription(false));
        message.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}
