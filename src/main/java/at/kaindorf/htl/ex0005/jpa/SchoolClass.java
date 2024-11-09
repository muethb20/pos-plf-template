package at.kaindorf.htl.ex0005.jpa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "schoolClass", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();
}
