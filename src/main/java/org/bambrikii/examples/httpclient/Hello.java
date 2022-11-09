package org.bambrikii.examples.httpclient;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;

import static org.bambrikii.examples.httpclient.HttpServerExample.DATE_TIME_FORMAT;

@ToString
@Getter
@Setter
public class Hello {
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private LocalDateTime dateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant timeStamp;
    private Long unixTime;
}
