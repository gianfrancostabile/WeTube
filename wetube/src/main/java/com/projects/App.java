package com.projects;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {
   public static void main(String[] args) {
      BasicConfigurator.configure();
      SpringApplication.run(App.class, args);
   }
}
