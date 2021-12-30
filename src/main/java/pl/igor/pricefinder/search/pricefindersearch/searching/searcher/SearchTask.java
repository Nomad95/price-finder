package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import org.springframework.context.ApplicationEvent;

import java.util.concurrent.Callable;

public interface SearchTask extends Callable<SearchStatus> {
    void stopExecution();

    boolean isFinished();

    Iterable<ApplicationEvent> getEvents();

    String getTaskName();
}
