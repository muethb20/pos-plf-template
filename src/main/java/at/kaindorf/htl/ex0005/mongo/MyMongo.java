package at.kaindorf.htl.ex0005.mongo;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;

public class MyMongo implements IDal {
    @Override
    public SchoolDto getAllStudents() {
        return null;
    }

    @Override
    public StudentDto getStudent(int id) {
        return null;
    }

    @Override
    public void deleteAllStudents() {

    }

    @Override
    public StudentDto save(StudentDto s) {
        return null;
    }

    @Override
    public void deleteStudent(int id) {

    }
}
