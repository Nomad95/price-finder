package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SiteConfigProvider;

public class FetchConfiguration extends AbstractSearchStep {

    private final SiteConfigProvider siteConfigProvider;
    private final String siteName;

    FetchConfiguration(SearchState searchState, SiteConfigProvider siteConfigProvider, String siteName) {
        super(searchState);
        this.siteConfigProvider = siteConfigProvider;
        this.siteName = siteName;
    }

    public FetchConfiguration(SiteConfigProvider siteConfigProvider, String siteName) {
        super(new SearchState());
        this.siteConfigProvider = siteConfigProvider;
        this.siteName = siteName;
    }

    @Override
    public void executeStep() {
        SiteConfig configForSite = siteConfigProvider.getConfigForSite(siteName);
        applySiteConfig(configForSite);

        markStepAsDone();
    }
}
