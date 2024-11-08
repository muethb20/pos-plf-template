package at.kaindorf.htl.ex0005.dal;

import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;

public interface IDal {
    public void save(StudentDto dto);
    public void deleteAllStudents();
    public SchoolDto getAllStudents();
}
