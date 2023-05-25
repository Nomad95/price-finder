package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

public interface SearchTaskBuilder {
    SearchTaskBuilder addStep(SearchStep searchStep);
    SearchTaskBuilder withName(String name);
    SearchTask build();
    SearchTaskBuilder withDelayer(SearchTaskDelayer delayer);
}
