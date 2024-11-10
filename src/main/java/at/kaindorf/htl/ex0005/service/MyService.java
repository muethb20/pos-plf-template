package at.kaindorf.htl.ex0005.service;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    @Autowired
    private IDal dal;
    public void deleteStudent(int id) {
        dal.deleteStudent(id);
    }

    public StudentDto saveStudent(StudentDto dto) {
        return dal.save(dto);
    }

    public SchoolDto getAllStudents() {
        return dal.getAllStudents();
    }

    public StudentDto getStudent(int id) {
        return dal.getStudent(id);
    }
}
