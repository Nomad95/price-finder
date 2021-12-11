package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(of = "taskName")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SiteSearcher implements Searcher {

    @NonNull
    private final SearchTask searchTask;
    private final String taskName;

    @Override
    public SearchTask createSearchTask() {
        return searchTask;
    }

}
