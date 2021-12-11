package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import pl.igor.pricefinder.search.pricefindersearch.searching.SearchStatus;

import java.util.concurrent.Callable;

public interface SearchTask extends Callable<SearchStatus> {
    void stopExecution();

    boolean isFinished();
}
