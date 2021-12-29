package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EventGatherer implements ApplicationEventPublisher {

    private List<Object> events = new ArrayList<>();

    @Override
    public void publishEvent(Object event) {
        log.info("i got an event!");
        events.add(event);
    }

    public int numberOfSentEvents() {
        return events.size();
    }
}
