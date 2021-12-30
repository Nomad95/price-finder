package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

public interface WebsiteSource {
    CharSequence html();

    String getElementHtmlById(String id);
}
