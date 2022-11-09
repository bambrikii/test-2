package org.bambrikii.examples.httpclient;

import java.time.Instant;
import java.time.LocalDateTime;

public class HelloFactory {
    private HelloFactory() {
    }

    public static Hello buildHello() {
        var helloRequest = new Hello();
        helloRequest.setMessage("msg1");
        helloRequest.setUnixTime(System.currentTimeMillis());
        helloRequest.setTimeStamp(Instant.now());
        helloRequest.setDateTime(LocalDateTime.now());
        return helloRequest;
    }
}
