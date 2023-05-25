package pl.igor.pricefinder.search.pricefindersearch.searching.core;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.RepeatableSearch;

import java.util.List;

public class SearchesHolder {

    private final List<RepeatableSearch> repeatableSearches;

    public SearchesHolder(List<RepeatableSearch> repeatableSearches) {
        this.repeatableSearches = repeatableSearches;
    }

    public synchronized void addSearcher(RepeatableSearch repeatableSearch) {
        repeatableSearches.add(repeatableSearch);
    }

    public synchronized List<RepeatableSearch> getAvailableSearchers() {
        return repeatableSearches;
    }
}
