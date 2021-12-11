package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchStep;

public interface SearchLinkFactory {
    SearchStep withLink(String url);
}
