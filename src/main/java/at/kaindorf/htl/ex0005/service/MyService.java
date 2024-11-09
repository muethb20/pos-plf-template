package at.kaindorf.htl.ex0005.service;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.SchoolClassDto;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MyService {

    @Autowired
    private IDal dal;
    public SchoolDto getAllStudents() {
        return dal.getAllStudents();
    }

    public StudentDto getStudentById(int id) {
        return dal.getStudentById(id);
    }

    public List<SchoolClassDto> getAllClasses() {
        return dal.getAllClasses();
    }
}
