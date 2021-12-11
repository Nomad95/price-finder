package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import pl.igor.pricefinder.search.pricefindersearch.searching.SearchTaskDelayer;

public interface SearcherBuilder {
    SearcherBuilder addStep(SearchStep searchStep);
    SearcherBuilder withName(String name);
    Searcher build();
    SearcherBuilder withDelayer(SearchTaskDelayer delayer);
}
