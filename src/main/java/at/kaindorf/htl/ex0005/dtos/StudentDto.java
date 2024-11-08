package at.kaindorf.htl.ex0005.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JacksonXmlRootElement(localName = "student")
@Data
public class StudentDto implements Serializable {

    @JsonProperty("name1")
    private String name;
    @JsonProperty("yearOfBirth1")
    private int yearOfBirth;
    @JsonProperty("initalLetter")
    private char initialLetter;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate localDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private LocalDateTime localDateTime;
    @JsonProperty("class")
    private String schoolClass;
}
