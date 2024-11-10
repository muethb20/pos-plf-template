package at.kaindorf.htl.ex0005.jpa;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Student {
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
