package pl.igor.pricefinder.search.pricefindersearch.searching;

import groovy.transform.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchTask;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.Searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class TestSearcherFactory {
    static Searcher createNewDefaultTestSearcher() {
        return new DefaultSearcher();
    }

    static Searcher createNewWithEventWhenFinishedTestSearcher() {
        return new EventWhenFinishSearcher();
    }
}

class DefaultSearcher implements Searcher {

    @Override
    public SearchTask createSearchTask() {
        return new DefaultSearchTask();
    }
}

class EventWhenFinishSearcher implements Searcher {

    @Override
    public SearchTask createSearchTask() {
        return new WithEventWhenFinishSearchTask();
    }
}

@EqualsAndHashCode(includes = "searchTaskId")
class DefaultSearchTask implements SearchTask {

    private boolean finished;
    private UUID searchTaskId = UUID.randomUUID();

    @Override
    public void stopExecution() {
        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public Iterable<ApplicationEvent> getEvents() {
        return List.of();
    }

    @Override
    public SearchStatus call() throws Exception {
        finished = true;
        return new SearchStatus();
    }
}

@EqualsAndHashCode(includes = "searchTaskId")
class WithEventWhenFinishSearchTask implements SearchTask {

    private boolean finished;
    private UUID searchTaskId = UUID.randomUUID();
    private final List<ApplicationEvent> events = new ArrayList<>();

    @Override
    public void stopExecution() {
        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public Iterable<ApplicationEvent> getEvents() {
        return new ArrayList<>(events);
    }

    @Override
    public SearchStatus call() throws Exception {
        finished = true;
        events.add(new TaskFinishedEvent(this));
        return new SearchStatus();
    }

    public static class TaskFinishedEvent extends ApplicationEvent {

        public TaskFinishedEvent(Object source) {
            super(source);
        }
    }
}



