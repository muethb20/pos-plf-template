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
    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleStudentNotFound(StudentNotFoundException ex, WebRequest wq) {
        ErrorMessage em = new ErrorMessage();

        em.setMessage(ex.getMessage());
        em.setDescription(wq.getDescription(true));
        em.setTimestamp(LocalDateTime.now());
        em.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(em, HttpStatus.NOT_FOUND);
    }
}
