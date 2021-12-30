package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import org.springframework.web.reactive.function.client.WebClient;

public interface WebClientProvider {
    WebClient getWithBaseUrl(String s);
}
