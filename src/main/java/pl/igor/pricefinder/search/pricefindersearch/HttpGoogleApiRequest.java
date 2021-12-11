package pl.igor.pricefinder.search.pricefindersearch;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.igor.pricefinder.search.pricefindersearch.searching.TaskScheduler;

@Component
@RequiredArgsConstructor
public class HttpGoogleApiRequest implements CommandLineRunner {

    public static final String KEY = "AIzaSyBBPdvGmJBXtrvqUaU7jzpipfzA7roteQM";
    public static final String CX = "c6bc8407bea1ee452";
    public static final String QUERY_STRING = "fuel cell rebel";

    private final TaskScheduler taskScheduler;

    @Override
    public void run(String... args) throws Exception {
        taskScheduler.fetchNewSearches();
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("key", KEY);
//        parameters.put("cx", CX);
//        parameters.put("q", QUERY_STRING);
//
//        WebClient client = WebClient.builder()
//                .baseUrl("https://www.googleapis.com")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(parameters)
//                .build();
//
//        String params = String.format("?key=%s&cx=%s&q=%s", KEY, CX, QUERY_STRING);
//
//        WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = client.get();
//        WebClient.RequestHeadersSpec<?> uri = requestHeadersUriSpec.uri("/customsearch/v1" + params);
//        Mono<String> rspns = uri.exchangeToMono(response -> {
//            if (response.statusCode()
//                    .equals(HttpStatus.OK)) {
//                return response.bodyToMono(String.class);
//            } else if (response.statusCode()
//                    .is4xxClientError()) {
//                return Mono.just("Error response");
//            } else {
//                return response.createException()
//                        .flatMap(Mono::error);
//            }
//        });
//
//        String block = rspns.block();
//        System.out.println(block);
//    }
}

