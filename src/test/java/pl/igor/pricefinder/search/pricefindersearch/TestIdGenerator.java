package pl.igor.pricefinder.search.pricefindersearch;

import pl.igor.pricefinder.search.pricefindersearch.searching.SearchIdGenerator;

import java.util.concurrent.atomic.AtomicLong;

public class TestIdGenerator implements SearchIdGenerator {

    private final AtomicLong counter = new AtomicLong(1);

    @Override
    public Long generateId() {
        return counter.getAndIncrement();
    }
}
