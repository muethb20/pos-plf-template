package at.kaindorf.htl.ex0005.controller;

import at.kaindorf.htl.ex0005.dtos.SchoolClassDto;
import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;
import at.kaindorf.htl.ex0005.event.RemoteInfo;
import at.kaindorf.htl.ex0005.service.MyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/")
@Slf4j
public class MyRestController {
    @Autowired
    private MyService service;


    @Value("json, postgresql")
    private String activeProfile;


    private final ApplicationEventPublisher publisher;

    public MyRestController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("getAllStudents")
    public ResponseEntity<SchoolDto> getAllStudents() {
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("getAllClasses")
    public ResponseEntity<List<SchoolClassDto>> getAllClasses() {
        return new ResponseEntity<List<SchoolClassDto>>(service.getAllClasses(), HttpStatus.OK);
    }

    @GetMapping("getStudent/{id}")
    public StudentDto getStudent(@PathVariable int id) {
        return service.getStudentById(id);
    }

    @GetMapping("getProfileName")
    public String getProfileName(HttpServletRequest request) {
        RemoteInfo ri = new RemoteInfo(request.getRemoteAddr(), request.getRemoteUser(), request.getHeader("User-Agent"));
        PayloadApplicationEvent<RemoteInfo> event = new PayloadApplicationEvent<>(this, ri);

        publisher.publishEvent(event);

        return activeProfile;
    }
}
