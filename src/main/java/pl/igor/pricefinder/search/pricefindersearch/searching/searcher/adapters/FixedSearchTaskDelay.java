package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters;

import lombok.AllArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchTaskDelayer;

@AllArgsConstructor
public class FixedSearchTaskDelay implements SearchTaskDelayer {
    private long delay;

    public long getNextDelay() {
        return delay;
    }
}
