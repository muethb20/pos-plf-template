package at.kaindorf.htl.ex0005.exception;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private String message;
    private String description;
    private LocalDateTime timestamp;
    private int statusCode;
}
