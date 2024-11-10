package at.kaindorf.htl.ex0005.configuration;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Profile("xml")
@Configuration
@Slf4j
public class DBInitXml implements ApplicationRunner {
    @Value("classpath:students.xml")
    private Resource resource;


    @Autowired
    private IDal dal;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("--> Reading from XML");
        dal.deleteAllStudents();
        XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());

        SchoolDto dto = mapper.readValue(resource.getFile(), SchoolDto.class);
        dto.getStudents().forEach(s -> dal.save(s));
    }

    @PreDestroy
    public void onDestroy() {
        log.info("--> Saving XML");
        Path path = Path.of("savedStudents.xml");

        XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            String xmlString = mapper.writeValueAsString(dal.getAllStudents());
            Files.writeString(path, xmlString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
