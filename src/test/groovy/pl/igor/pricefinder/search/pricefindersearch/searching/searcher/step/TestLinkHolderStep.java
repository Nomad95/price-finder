package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = "link", callSuper = false)
class TestLinkHolderStep extends AbstractSearchStep {
    @Getter
    private final String link;

    TestLinkHolderStep(SearchState searchState, String link) {
        super(searchState);
        this.link = link;
    }

    @Override
    public void executeStep() {
        markStepAsDone();
    }
}
