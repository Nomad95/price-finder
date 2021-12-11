package pl.igor.pricefinder.search.pricefindersearch.searching;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application.search.config")
public class SearchConfig {
    private final int maxOngoingSearches;
    private final int taskDelayInSeconds;
}
