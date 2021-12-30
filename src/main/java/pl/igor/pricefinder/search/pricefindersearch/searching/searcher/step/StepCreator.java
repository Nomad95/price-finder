package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.*;

public class StepCreator {
    public static SearchStep createNewBalanceFindMenuLinksStep(String mainSite, WebsiteScrapper websiteScrapper) {
        return new NewBalanceFindMenuLinks(new SearchState(), mainSite, websiteScrapper);
    }

    public static SearchStep createSplitMenuLinksStep(SearchLinkFactory searchLinkFactory) {
        return new SplitSearchLinksStep(new SearchState(), searchLinkFactory);
    }

    public static SearchStep createSaveStep(ProductsSaver productsSaver) {
        return new SaveProducts(new SearchState(), productsSaver);
    }

    public static SearchLinkFactory createSearchNewBalanceUrlStepFactory(WebsiteScrapper websiteScrapper, WebClientProvider webClientProvider) {
        return url -> new NewBalanceSearchUrl(new SearchState(), websiteScrapper, webClientProvider, url);
    }

    public static SearchStep createConfigurationStep(SearchIdGenerator searchIdGenerator) {
        return new ConfigurationStep(new SearchState(), searchIdGenerator);
    }
}
