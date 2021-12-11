package pl.igor.pricefinder.search.pricefindersearch.searching;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FixedSearchTaskDelay implements SearchTaskDelayer {
    private long delay;

    public long getNextDelay() {
        return delay;
    }
}
