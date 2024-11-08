package at.kaindorf.htl.ex0005.database;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Configuration("myDBInitJson")
@Profile("json")
@Slf4j
public class DBInitJson implements CommandLineRunner {
    @Value("classpath:students.json")
    private Resource resource;

    @Autowired
    private IDal dal;
    @Autowired

    private ObjectMapper om;

    @PostConstruct
    private void init() {
        log.info("--> Read from JSON");
    }

    @PreDestroy
    public void onDestroy()
    {
        try
        {
            SchoolDto sd = dal.getAllStudents();
            Path JSON_FILE = Path.of("data.json");
            String jsonString =
                    om.writerWithDefaultPrettyPrinter().writeValueAsString(sd);
            Files.writeString(JSON_FILE, jsonString);
        }
        catch(Exception ex)
        {
            log.error(ex.toString());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        dal.deleteAllStudents();

        List<StudentDto> list = om.readValue(resource.getFile(), new TypeReference<List<StudentDto>>() {});

        list.forEach(dto -> dal.save(dto));
    }
}
