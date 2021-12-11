package pl.igor.pricefinder.search.pricefindersearch.searching;

import groovy.transform.EqualsAndHashCode;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchTask;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.Searcher;

import java.util.UUID;

class TestSearcherFactory {
    static Searcher createNewDefaultTestSearcher() {
        return new DefaultSearcher();
    }
}

class  DefaultSearcher implements Searcher {

    @Override
    public SearchTask createSearchTask() {
        return new DefaultSearchTask();
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
    public SearchStatus call() throws Exception {
        finished = true;
        return new SearchStatus();
    }
}
