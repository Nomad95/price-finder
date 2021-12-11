package pl.igor.pricefinder.search.pricefindersearch.searching;

import java.util.function.Supplier;

class NoDelayTaskExecutionDelayer implements Supplier<TaskExecutionDelayer> {
    @Override
    public TaskExecutionDelayer get() {
        return () -> 0;
    }
}
