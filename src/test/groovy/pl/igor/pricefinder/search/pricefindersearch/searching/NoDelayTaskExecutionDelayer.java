package pl.igor.pricefinder.search.pricefindersearch.searching;

import pl.igor.pricefinder.search.pricefindersearch.searching.core.TaskExecutionDelayer;

import java.util.function.Supplier;

public class NoDelayTaskExecutionDelayer implements Supplier<TaskExecutionDelayer> {
    @Override
    public TaskExecutionDelayer get() {
        return () -> 0;
    }
}
