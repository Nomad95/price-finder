package pl.igor.pricefinder.search.pricefindersearch.searching;

public interface WebsiteSource {
    CharSequence html();

    String getElementHtmlById(String id);
}
