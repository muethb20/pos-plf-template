package at.kaindorf.htl.ex0005.database;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.StudentDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.util.List;

@Profile("json")
@Slf4j
@Configuration
public class DBInitJson implements CommandLineRunner {

    @Value("classpath:students.json")
    private Resource resource;

    @Autowired
    private IDal dal;

    @Autowired
    private ObjectMapper om;

    @PostConstruct
    private void init() {
        log.info("--> Starting read from JSON");
    }

    @Override
    public void run(String... args) throws Exception {
        dal.deleteAllStudents();
        List<StudentDto> list = om.readValue(resource.getFile(), new TypeReference<List<StudentDto>>() {});

        list.forEach(dto -> dal.save(dto));
    }
}
