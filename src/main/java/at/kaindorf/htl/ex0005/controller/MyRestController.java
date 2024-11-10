package at.kaindorf.htl.ex0005.controller;

import at.kaindorf.htl.ex0005.dtos.SchoolDto;
import at.kaindorf.htl.ex0005.dtos.StudentDto;
import at.kaindorf.htl.ex0005.event.RemoteInfo;
import at.kaindorf.htl.ex0005.jpa.Student;
import at.kaindorf.htl.ex0005.service.MyService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("api")
public class MyRestController {
    @Autowired
    private MyService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("getStudents")
    public ResponseEntity<SchoolDto> getAllStudents () {
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<StudentDto> postStudent(@Valid @RequestBody StudentDto dto){
        return new ResponseEntity<>(service.saveStudent(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("student/{id}")
    public void deleteStudent (@PathVariable int id, HttpServletRequest hsr) {
        service.deleteStudent(id);

        RemoteInfo ri = new RemoteInfo(hsr.getRemoteHost(), hsr.getHeader("User-Agent"));

        publisher.publishEvent(new PayloadApplicationEvent<>(this, ri));
    }

    @PostMapping("upload")
    public boolean uploadFile(@RequestParam("file") MultipartFile file) {
        String dir = file.getOriginalFilename();

        try {
            file.transferTo(Path.of(dir));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @GetMapping("download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        Path file = Path.of(fileName);

        try {
            byte[] fileData = Files.readAllBytes(file);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("student/{id}")
    public ResponseEntity<StudentDto> getStudent (@PathVariable int id) {
        return new ResponseEntity<>(service.getStudent(id), HttpStatus.OK);
    }
}
