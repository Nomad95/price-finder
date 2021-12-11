package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchStep;

@Slf4j
class SplitSearchLinksStep extends AbstractSearchStep {
    private final SearchLinkFactory stepFactory;

    public SplitSearchLinksStep(@NonNull SearchState searchState, @NonNull SearchLinkFactory stepFactory) {
        super(searchState);
        this.stepFactory = stepFactory;
    }

    @Override
    public void executeStep() {
        log.info("Splitting all urls to new search task");
        SearchStep currentAppendedStep = this;
        while (hasNextLink()) {
            String url = fetchNextLink();
            SearchStep searchLinkStep = stepFactory.withLink(url);
            currentAppendedStep.appendNextStep(searchLinkStep);
            currentAppendedStep = searchLinkStep;
        }

        log.info("Finished splitting all urls to new search task");
        markStepAsDone();
    }
}
