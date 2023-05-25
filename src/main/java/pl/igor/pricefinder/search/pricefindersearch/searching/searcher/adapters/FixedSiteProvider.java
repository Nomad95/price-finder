package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SitesProvider;

public class FixedSiteProvider implements SitesProvider {
    @Override
    public String getNextSiteToSearch() {
        return "https://nbsklep.pl";
    }
}
