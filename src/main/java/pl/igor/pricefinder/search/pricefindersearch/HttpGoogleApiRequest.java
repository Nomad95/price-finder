package pl.igor.pricefinder.search.pricefindersearch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class HttpGoogleApiRequest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", "AIzaSyBBPdvGmJBXtrvqUaU7jzpipfzA7roteQM");
        parameters.put("cx", "c6bc8407bea1ee452");
        parameters.put("q", "fuel cell rebel");

        WebClient client = WebClient.builder()
                .baseUrl("https://www.googleapis.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(parameters)
                .build();

        WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = client.get();
        WebClient.RequestHeadersSpec<?> uri = requestHeadersUriSpec.uri("/customsearch/v1");
        Mono<String> rspns = uri.exchangeToMono(response -> {
            if (response.statusCode()
                    .equals(HttpStatus.OK)) {
                return response.bodyToMono(String.class);
            } else if (response.statusCode()
                    .is4xxClientError()) {
                return Mono.just("Error response");
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });

        String block = rspns.block();
        System.out.println(block);
    }
}

