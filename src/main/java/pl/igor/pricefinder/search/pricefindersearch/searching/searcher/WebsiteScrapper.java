package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import java.util.Optional;

public interface WebsiteScrapper {
    Optional<WebsiteSource> getByUrl(String url);
}
