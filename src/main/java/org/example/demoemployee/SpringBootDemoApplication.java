package org.example.demoemployee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.Scanner;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoApplication.class, args);

        UserService userService = context.getBean(UserService.class);
        UserRegistrationService registrationService = new UserRegistrationService(userService);

        Scanner scanner = new Scanner(System.in);
        registrationService.performUserActions(scanner);

        scanner.close();
    }
}
