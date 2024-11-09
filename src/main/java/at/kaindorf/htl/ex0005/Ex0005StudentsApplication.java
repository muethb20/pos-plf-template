package at.kaindorf.htl.ex0005;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
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
    ApplicationContext ctx = SpringApplication.run(Ex0005StudentsApplication.class, args);
    Environment env = ctx.getBean(Environment.class);
    log.info("--> Profile "+env.getProperty("spring.profiles.active"));
  }

}
