package at.kaindorf.htl.ex0005.configuration;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.StudentDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Profile("json")
@Configuration
@Slf4j
public class DBInitJson implements ApplicationRunner {
    @Value("classpath:students.json")
    private Resource resource;

    @Autowired
    private IDal dal;

    @Autowired
    private ObjectMapper om;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("--> Reading from JSON");
        dal.deleteAllStudents();
        List<StudentDto> students = om.readValue(resource.getFile(), new TypeReference<List<StudentDto>>() {});

        for (StudentDto student : students) {
            dal.save(student);
        }
    }

    @PreDestroy
    public void onDestroy() {
        log.info("--> Saving JSON");

        Path jsonFile = Path.of("studentsSaved.json");

        try {
            String jsonString = om.writerWithDefaultPrettyPrinter().writeValueAsString(dal.getAllStudents());
            Files.writeString(jsonFile, jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
