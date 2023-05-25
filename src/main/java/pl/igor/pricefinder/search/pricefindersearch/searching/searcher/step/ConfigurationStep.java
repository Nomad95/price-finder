package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import lombok.NonNull;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchIdGenerator;

import java.util.Objects;
import java.util.Optional;

public class ConfigurationStep extends AbstractSearchStep {

    private final SearchIdGenerator searchIdGenerator;
    private final Optional<String> startUrl;

    ConfigurationStep(SearchState searchState, SearchIdGenerator searchIdGenerator) {
        super(searchState);
        this.searchIdGenerator = searchIdGenerator;
        this.startUrl = Optional.empty();
    }

    ConfigurationStep(SearchState searchState, SearchIdGenerator searchIdGenerator, @NonNull String startUrl) {
        super(searchState);
        this.searchIdGenerator = searchIdGenerator;
        this.startUrl = Optional.of(startUrl);
    }

    @Override
    public void executeStep() {
        Long id = Objects.requireNonNull(searchIdGenerator.generateId());
        setSearchId(id);
        setSearchDateAsNow();
        startUrl.ifPresent(this::addLinkToVisit);

        markStepAsDone();
    }

}
