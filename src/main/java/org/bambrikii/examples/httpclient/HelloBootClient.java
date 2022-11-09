package org.bambrikii.examples.httpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@SpringBootApplication
public class HelloBootClient {
    public static void main(String[] args) {
        var ctx = SpringApplication
                .run(HelloBootClient.class, args);
        var webClient = ctx.getBean("webClient", WebClient.class);
        Hello hello = HelloFactory.buildHello();
        var response = webClient
                .post()
                .uri("/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(hello)
                .retrieve()
                .bodyToFlux(Hello.class)
                .blockFirst();
        System.out.println(response);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080/api/v1")
                .build();
    }
}
