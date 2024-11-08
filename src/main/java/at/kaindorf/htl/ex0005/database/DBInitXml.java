package at.kaindorf.htl.ex0005.database;

import at.kaindorf.htl.ex0005.dal.IDal;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Path;

@Configuration("myDBInitXml")
@Profile("xml")
@Slf4j
public class DBInitXml implements CommandLineRunner {
    @Autowired
    private IDal dal;

    @Value("classpath:students.xml")
    private Resource resource;

    @Override
    public void run(String... args) throws Exception {
        log.info("--> Reading XML!");
        dal.deleteAllStudents();
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());

        SchoolDto sd = xmlMapper.readValue(resource.getFile(), SchoolDto.class);
        sd.getStudents().forEach(dto -> dal.save(dto));
    }

    @PreDestroy
    public void onDestroy()
    {
        log.info("--> DBInitXML / onDestroy");
        try
        {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

            Path xmlFile = Path.of("students.xml");

            SchoolDto sd = dal.getAllStudents();
            String xml = xmlMapper.writeValueAsString(sd);
            Files.writeString(xmlFile,xml);
        }
        catch(Exception ex)
        {
            log.error(ex.toString());
        }
    }
}
