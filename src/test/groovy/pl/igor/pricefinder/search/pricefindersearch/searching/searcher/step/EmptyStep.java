package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmptyStep extends AbstractSearchStep {

    private final int index;

    public EmptyStep(int index) {
        super(new SearchState());
        this.index = index;
    }

    @Override
    public void executeStep() throws Exception {
        log.info("Executing: {}", index);
        markStepAsDone();
    }
}
