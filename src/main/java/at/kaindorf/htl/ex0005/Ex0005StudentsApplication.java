package at.kaindorf.htl.ex0005;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class Ex0005StudentsApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(Ex0005StudentsApplication.class, args);
  }

}
