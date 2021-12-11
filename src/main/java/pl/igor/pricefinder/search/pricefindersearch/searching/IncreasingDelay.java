package pl.igor.pricefinder.search.pricefindersearch.searching;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IncreasingDelay implements TaskExecutionDelayer {
    private long currentDelay;
    private final long step;

    public long getNextDelay() {
        var nextDelay = this.currentDelay;
        currentDelay += step;
        return nextDelay;
    }
}
