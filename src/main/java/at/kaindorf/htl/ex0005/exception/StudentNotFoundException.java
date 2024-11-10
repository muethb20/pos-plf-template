package at.kaindorf.htl.ex0005.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String msg) {
        super(msg);
    }
}
