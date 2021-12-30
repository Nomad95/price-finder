package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step.SiteConfig;

public interface SiteConfigProvider {
    SiteConfig getConfigForSite(String siteName);
}
