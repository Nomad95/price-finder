package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

public interface SearcherBuilder {
    SearcherBuilder addStep(SearchStep searchStep);
    SearcherBuilder withName(String name);
    RepeatableSearch build();
    SearcherBuilder withDelayer(SearchTaskDelayer delayer);
}
