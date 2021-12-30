package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class SearchFactory {
    public static SearcherBuilder buildSiteSearch() {
        return new SiteSearcherBuilder();
    }
}

class SiteSearcherBuilder implements SearcherBuilder {

    private final Deque<SearchStep> steps;
    private SearchTaskDelayer searchTaskDelayer = () -> 0;
    private String taskName;
    //time execution constraints

    SiteSearcherBuilder() {
        this.steps = new ArrayDeque<>();
    }

    @Override
    public SearcherBuilder withName(String name) {
        this.taskName = name;
        return this;
    }

    @Override
    public SearcherBuilder addStep(SearchStep searchStep) {
        SearchStep peek = steps.peekLast();
        if (Objects.nonNull(peek)) {
            peek.appendNextStep(searchStep);
        }
        searchStep.setPreviousStep(peek);
        steps.add(searchStep);
        return this;
    }

    @Override
    public Searcher build() {
        //TODO: take care of delays
        SiteSearchTaskImpl searchTask = new SiteSearchTaskImpl(steps.pop(), searchTaskDelayer, taskName);
        return new SiteSearcher(searchTask, taskName);
    }

    @Override
    public SearcherBuilder withDelayer(SearchTaskDelayer delayer) {
        this.searchTaskDelayer = delayer;
        return this;
    }
}
