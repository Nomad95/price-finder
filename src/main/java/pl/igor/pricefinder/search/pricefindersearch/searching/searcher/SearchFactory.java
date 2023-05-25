package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class SearchFactory {
    public static SearcherBuilder buildSiteSearcher() {
        return new SiteSearcherBuilder();
    }

    public static SearchTaskBuilder buildSiteSearchTask() {
        return new SiteSearchTaskBuilder();
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
    public RepeatableSearch build() {
        SiteSearchTaskImpl searchTask = new SiteSearchTaskImpl(steps.pop(), searchTaskDelayer, taskName);
        return new SiteSearcher(searchTask, taskName);
    }

    @Override
    public SearcherBuilder withDelayer(SearchTaskDelayer delayer) {
        this.searchTaskDelayer = delayer;
        return this;
    }
}

class SiteSearchTaskBuilder implements SearchTaskBuilder {

    private final Deque<SearchStep> steps;
    private SearchTaskDelayer searchTaskDelayer = () -> 0;
    private String taskName;

    SiteSearchTaskBuilder() {
        this.steps = new ArrayDeque<>();
    }

    @Override
    public SearchTaskBuilder withName(String name) {
        this.taskName = name;
        return this;
    }

    @Override
    public SearchTaskBuilder addStep(SearchStep searchStep) {
        SearchStep peek = steps.peekLast();
        if (Objects.nonNull(peek)) {
            peek.appendNextStep(searchStep);
        }
        searchStep.setPreviousStep(peek);
        steps.add(searchStep);
        return this;
    }

    @Override
    public SearchTask build() {
        return new SiteSearchTaskImpl(steps.pop(), searchTaskDelayer, taskName);
    }

    @Override
    public SearchTaskBuilder withDelayer(SearchTaskDelayer delayer) {
        this.searchTaskDelayer = delayer;
        return this;
    }
}