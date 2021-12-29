package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import org.springframework.context.ApplicationEvent;

public interface SearchStep {
    boolean hasNextStep();

    void setNextStep(SearchStep searchStep);

    void setPreviousStep(SearchStep peek);

    void executeStep() throws Exception; //TODO: searchException

    boolean isDone();

    void appendNextStep(SearchStep searchLinkStep);

    SearchStep getNextStep();

    void init();

    Iterable<ApplicationEvent> getAllEvents();
}
