package org.bambrikii.examples.httpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;

import static org.bambrikii.examples.httpclient.HelloFactory.buildHello;

@Configuration
@RequestMapping("/api/v1")
@RestController
@SpringBootApplication
public class HttpServerExample {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        SpringApplication.run(HttpServerExample.class, args);
    }

    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity<Hello> hello() {
        var hello = new Hello();
        hello.setMessage("Message " + System.nanoTime());
        hello.setDateTime(LocalDateTime.now());
        hello.setTimeStamp(Instant.now());
        hello.setUnixTime(System.currentTimeMillis());
        return ResponseEntity.ok(hello);
    }

    @PostMapping("/hello")
    @ResponseBody
    public ResponseEntity<Hello> hello(@RequestBody Hello request) {
        System.out.println(request);
        return ResponseEntity.ok(buildHello());
    }


    @GetMapping("/hellos")
    @ResponseBody
    public ResponseEntity<Hello[]> hellos() {
        var hello = buildHello();
        return ResponseEntity.ok(new Hello[]{hello, hello, hello});
    }
}
