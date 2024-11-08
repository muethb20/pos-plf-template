package at.kaindorf.htl.ex0005.jpa;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int yearOfBirth;
    private char initialLetter;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    @ManyToOne
    private SchoolClass schoolClass;

}
