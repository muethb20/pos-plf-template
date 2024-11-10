package at.kaindorf.htl.ex0005.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JacksonXmlRootElement(localName = "student")
public class StudentDto {
    private int id;

    @JsonAlias("initalLetter")
    private String initialLetter;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate localDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    @JsonAlias("name1")
    private String name;

    @JsonAlias("yearOfBirth1")
    private int yearOfBirth;

    @JsonAlias("class")
    private String className;
}
