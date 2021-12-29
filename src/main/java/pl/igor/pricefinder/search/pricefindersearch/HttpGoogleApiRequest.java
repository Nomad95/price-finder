package pl.igor.pricefinder.search.pricefindersearch;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import pl.igor.pricefinder.search.pricefindersearch.searching.TaskScheduler;

//@Component
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
}

