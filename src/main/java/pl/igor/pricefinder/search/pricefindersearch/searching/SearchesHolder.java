package pl.igor.pricefinder.search.pricefindersearch.searching;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.Searcher;

import java.util.List;

public class SearchesHolder {

    private final List<Searcher> searchers;

    public SearchesHolder(List<Searcher> searchers) {
        this.searchers = searchers;
    }

    public synchronized void addSearcher(Searcher searcher) {
        searchers.add(searcher);
    }

    public synchronized List<Searcher> getAvailableSearches() {
        return searchers;
    }
}
