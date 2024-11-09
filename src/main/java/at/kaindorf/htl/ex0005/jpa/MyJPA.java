package at.kaindorf.htl.ex0005.jpa;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.SchoolClassDto;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;
import at.kaindorf.htl.ex0005.exception.StudentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Profile("postgresql | mysql")
public class MyJPA implements IDal {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Override
    public void save(StudentDto dto) {

        log.info("New Student " + studentRepository.save(convertToStudent(dto)));
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    @Override
    public SchoolDto getAllStudents() {

        List<Student> students = studentRepository.findAll();

       /* List<StudentDto> studentDtoList = new ArrayList<>();

        for (Student student : students) {
            StudentDto dto = new StudentDto();
            studentDtoList.add(convertToStudentDto(student));
        }*/

        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setStudents(studentRepository.findAll()
                .stream()
                .map(this::convertToStudentDto)
                .collect(Collectors.toList()));

        return schoolDto;
    }

    @Override
    public StudentDto getStudentById(int id) {

        if (studentRepository.findById(id).isPresent()){
            return convertToStudentDto(studentRepository.findById(id).get());
        } else {
            throw new StudentNotFoundException("Student with id " + id + " does not exist!");
        }


    }

    @Override
    public List<SchoolClassDto> getAllClasses() {
        return schoolClassRepository.findAll().stream().map(this::convertToSchoolClassDto).collect(Collectors.toList());
    }

    private SchoolClassDto convertToSchoolClassDto(SchoolClass sc) {
        SchoolClassDto dto = new SchoolClassDto();
        dto.setName(sc.getName());
        return dto;
    }

    private Student convertToStudent(StudentDto dto) {
        Student s = new Student();
        s.setName(dto.getName());
        s.setInitialLetter(dto.getInitialLetter().charAt(0));
        s.setLocalDate(dto.getLocalDate());
        s.setLocalDateTime(dto.getLocalDateTime());
        s.setYearOfBirth(dto.getYearOfBirth());
        s.setSchoolClass(findSchoolClassByString(dto.getClassName(), s));

        return s;
    }

    private SchoolClass findSchoolClassByString (String className, Student s) {
        SchoolClass sc = schoolClassRepository.findSchoolClassByName(className);

        if (sc == null) {
            sc = new SchoolClass();
            sc.setName(className);
        }

        sc.getStudents().add(s);

        return schoolClassRepository.save(sc);
    }

    private StudentDto convertToStudentDto(Student student) {
        StudentDto dto = new StudentDto();

        dto.setLocalDate(student.getLocalDate());
        dto.setInitialLetter(student.getInitialLetter() + "");
        dto.setLocalDateTime(student.getLocalDateTime());
        dto.setYearOfBirth(student.getYearOfBirth());
        dto.setName(student.getName());
        dto.setClassName(student.getSchoolClass().getName());

        return dto;
    }
}
