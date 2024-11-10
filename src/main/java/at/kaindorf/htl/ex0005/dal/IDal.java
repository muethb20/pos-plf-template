package at.kaindorf.htl.ex0005.dal;

import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;

public interface IDal {
    SchoolDto getAllStudents();
    StudentDto getStudent(int id);
    void deleteAllStudents();

    StudentDto save(StudentDto s);
    void deleteStudent(int id);
}
