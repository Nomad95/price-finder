package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import pl.igor.pricefinder.search.pricefindersearch.searching.SiteConfigProvider;

public class FetchMenuLinks extends AbstractSearchStep {

    private final SiteConfigProvider siteConfigProvider;
    private final String siteName;

    FetchMenuLinks(SearchState searchState, SiteConfigProvider siteConfigProvider, String siteName) {
        super(searchState);
        this.siteConfigProvider = siteConfigProvider;
        this.siteName = siteName;
    }

    public FetchMenuLinks(SiteConfigProvider siteConfigProvider, String siteName) {
        super(new SearchState());
        this.siteConfigProvider = siteConfigProvider;
        this.siteName = siteName;
    }

    @Override
    public void executeStep() {


        markStepAsDone();
    }
}
