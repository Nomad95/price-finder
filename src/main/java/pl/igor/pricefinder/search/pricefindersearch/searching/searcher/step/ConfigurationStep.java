package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchIdGenerator;

import java.util.Objects;

public class ConfigurationStep extends AbstractSearchStep {

    private final SearchIdGenerator searchIdGenerator;

    ConfigurationStep(SearchState searchState, SearchIdGenerator searchIdGenerator) {
        super(searchState);
        this.searchIdGenerator = searchIdGenerator;
    }

    @Override
    public void executeStep() {
        Long id = Objects.requireNonNull(searchIdGenerator.generateId());
        setSearchId(id);
        setSearchDateAsNow();

        markStepAsDone();
    }

}
