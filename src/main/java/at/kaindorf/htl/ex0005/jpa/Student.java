package at.kaindorf.htl.ex0005.jpa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private char initialLetter;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private String name;
    private int yearOfBirth;
    @ManyToOne
    private SchoolClass schoolClass;

}
