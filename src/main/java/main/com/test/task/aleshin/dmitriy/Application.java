package main.com.test.task.aleshin.dmitriy;

import main.com.test.task.aleshin.dmitriy.config.Config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class<?>[] {Application.class, Config.class}, args);
    }
}
