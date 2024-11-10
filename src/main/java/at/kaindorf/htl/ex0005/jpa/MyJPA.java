package at.kaindorf.htl.ex0005.jpa;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;
import at.kaindorf.htl.ex0005.exception.StudentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Profile("postgresql | mysql")
@Component
@Slf4j
public class MyJPA implements IDal {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Override
    public SchoolDto getAllStudents() {
        SchoolDto sc = new SchoolDto();
        sc.setStudents(studentRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList()));
        return sc;
    }

    @Override
    public StudentDto getStudent(int id) {
        if (studentRepository.findById(id).isPresent()) {
            Student s = studentRepository.findById(id).get();
            return convertEntityToDto(s);
        } else {
            throw new StudentNotFoundException("Student with id " + id + " not found!");
        }

    }

    private StudentDto convertEntityToDto(Student s) {
        StudentDto dto = new StudentDto();

        dto.setId(s.getId());
        dto.setClassName(s.getSchoolClass().getName());
        dto.setLocalDate(s.getLocalDate());
        dto.setLocalDateTime(s.getLocalDateTime());
        dto.setYearOfBirth(s.getYearOfBirth());
        dto.setInitialLetter(s.getInitialLetter()+"");
        dto.setName(s.getName());

        return dto;
    }


    @Override
    public void deleteAllStudents() {
        log.info("--> Deleting all Students!");
        studentRepository.deleteAll();
    }

    @Override
    public StudentDto save(StudentDto s) {
        log.info("--> New Student " + s);
        return convertEntityToDto(studentRepository.save(convertDtoToEntity(s)));
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    private Student convertDtoToEntity(StudentDto s) {
        Student student = new Student();

        student.setName(s.getName());
        student.setLocalDate(s.getLocalDate());
        student.setInitialLetter(s.getInitialLetter().charAt(0));
        student.setLocalDateTime(s.getLocalDateTime());
        student.setYearOfBirth(s.getYearOfBirth());

        SchoolClass sc = schoolClassRepository.findSchoolClassByName(s.getClassName());
        if (sc == null) {
            sc = new SchoolClass();
            sc.setName(s.getClassName());
        }
        sc.getStudents().add(student);
        schoolClassRepository.save(sc);
        student.setSchoolClass(sc);

        return student;
    }
}
