package at.kaindorf.htl.ex0005.database;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.nio.file.Path;

@Profile("xml")
@Slf4j
@Configuration
public class DBInitXml implements ApplicationRunner {

    @Autowired
    private IDal dal;

    @Value("classpath:students.xml")
    private Resource resource;

    @PostConstruct
    private void init() {
        log.info("--> Starting read from XML");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dal.deleteAllStudents();

        XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());

        SchoolDto sd = mapper.readValue(resource.getFile(), SchoolDto.class);
        sd.getStudents().forEach(dto -> dal.save(dto));
    }
}

