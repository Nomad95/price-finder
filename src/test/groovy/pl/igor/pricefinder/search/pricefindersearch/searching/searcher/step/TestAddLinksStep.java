package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import java.util.List;

class TestAddLinksStep extends AbstractSearchStep {

    private final List<String> links;

    TestAddLinksStep(SearchState searchState, List<String> links) {
        super(searchState);
        this.links = links;
    }

    @Override
    public void executeStep() throws Exception {
        links.forEach(this::addLinkToVisit);
        markStepAsDone();
    }
}
