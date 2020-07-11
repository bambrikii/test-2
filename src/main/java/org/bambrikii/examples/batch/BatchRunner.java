package org.bambrikii.examples.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchRunner {
    public static void main(String[] args) {
        SpringApplication.run(BatchConfiguration.class, args);
    }
}
