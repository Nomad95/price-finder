package pl.igor.pricefinder.search.pricefindersearch;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.igor.pricefinder.search.pricefindersearch.searching.core.TaskScheduler;

@Component
@RequiredArgsConstructor
public class HttpGoogleApiRequest implements CommandLineRunner {

    private final TaskScheduler taskScheduler;

    @Override
    public void run(String... args) throws Exception {
        taskScheduler.fetchNewSearches();
    }
}

