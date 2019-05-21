package be.appelicious;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class Go4FitMain {

    public static void main(String[] args) {
        SpringApplication.run(Go4FitMain.class, args);
    }
}
