package at.kaindorf.htl.ex0005.jpa;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Profile("postgresql | mysql")
@Slf4j
public class MyJPA implements IDal {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Override
    public void save(StudentDto dto) {
        log.info(dto.toString());
        studentRepository.save(convertToStudent(dto));
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
        schoolClassRepository.deleteAll();
    }

    @Override
    public SchoolDto getAllStudents() {
        SchoolDto dto = new SchoolDto();
        dto.setStudents(studentRepository.findAll().stream().map(this::convertToStudentDto).collect(Collectors.toList()));
        return dto;
    }

    private Student convertToStudent(StudentDto dto) {
        Student s = new Student();
        s.setName(dto.getName());
        s.setInitialLetter(dto.getInitialLetter());
        s.setLocalDate(dto.getLocalDate());
        s.setLocalDateTime(dto.getLocalDateTime());
        s.setYearOfBirth(dto.getYearOfBirth());
        s.setSchoolClass(findSchoolClassByString(dto.getSchoolClass(), s));
        return s;
    }

    private SchoolClass findSchoolClassByString(String name, Student s) {
        SchoolClass sc =  schoolClassRepository.findSchoolClassByName(name);


        if (sc == null) {
            sc = new SchoolClass();
            sc.setName(name);
            return schoolClassRepository.save(sc);
        }

        sc.getStudents().add(s);

        return sc;
    }

    private StudentDto convertToStudentDto(Student s) {
        StudentDto dto = new StudentDto();
        dto.setInitialLetter(s.getInitialLetter());
        dto.setLocalDateTime(s.getLocalDateTime());
        dto.setLocalDate(s.getLocalDate());
        dto.setYearOfBirth(s.getYearOfBirth());
        dto.setName(s.getName());
        dto.setSchoolClass(s.getSchoolClass().getName());
        return dto;
    }
}
