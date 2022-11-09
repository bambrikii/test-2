package org.bambrikii.examples.httpclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.bambrikii.examples.httpclient.HelloFactory.buildHello;
import static org.bambrikii.examples.httpclient.HttpServerExample.DATE_TIME_FORMAT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * https://docs.oracle.com/en/java/javase/12/docs/api/java.net.http/java/net/http/HttpClient.html
 */
public class HttpClientExample {

    public static final String url1 = "http://localhost:8080/api/v1/hello";
    public static final String url2 = "http://localhost:8080/api/v1/hellos";

    public static void main(String[] args) throws IOException, InterruptedException {
//        javaHttp();
//        javaCore2();
//        javaCore3();
        javaCore4();
    }

    private static Gson crateGson() {
        JsonDeserializer<LocalDateTime> localDateTimeDeserializer = new JsonDeserializer<>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                String str = json.getAsJsonPrimitive().getAsString();
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
            }
        };
        JsonDeserializer<Instant> instantDeserializer = new JsonDeserializer<>() {
            @Override
            public Instant deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                String str = json.getAsJsonPrimitive().getAsString();
                return Instant.parse(str);
            }
        };
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, localDateTimeDeserializer)
                .registerTypeAdapter(Instant.class, instantDeserializer)
                .create();
        return gson;
    }

    private static final Gson GSON = crateGson();

    private static void javaHttp() throws IOException, InterruptedException {
        var client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        var request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(url1))
                .build();

        var response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    private static void javaCore2() throws IOException {
        try (java.io.InputStream is = new URL(url1).openStream()) {
            String contents = new String(is.readAllBytes());
            System.out.println(contents);
            JsonElement element = JsonParser.parseString(contents);
            Hello hello = GSON
                    .fromJson(contents, Hello.class);
            System.out.println(hello);
        }
    }

    private static void javaCore3() throws IOException {
        URL url = new URL(url1);
        HttpURLConnection cn = (HttpURLConnection) url.openConnection();
        cn.setDoOutput(true);
        cn.setRequestMethod("POST");
        try (DataOutputStream dos = new DataOutputStream(cn.getOutputStream());) {
            Hello helloRequest = buildHello();
            String body = GSON.toJson(helloRequest);
            byte[] bytes = body.getBytes();
            dos.write(bytes);
            dos.close();
            try (InputStream is = cn.getInputStream()) {
                String response = new String(is.readAllBytes());
                Hello hello = GSON.fromJson(response, Hello.class);
                System.out.println(hello);
            }
        }
    }

    private static void javaCore4() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        String request = GSON.toJson(buildHello());
        HttpRequest rq = HttpRequest
                .newBuilder(URI.create(url1))
                .header("Accept", APPLICATION_JSON_VALUE)
                .header("Content-Type", APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(request))
                .build();
        var responseString = client.send(rq, HttpResponse.BodyHandlers.ofString())
                .body();
        Hello response = GSON.fromJson(responseString, Hello.class);
        System.out.println(response);
    }
}
