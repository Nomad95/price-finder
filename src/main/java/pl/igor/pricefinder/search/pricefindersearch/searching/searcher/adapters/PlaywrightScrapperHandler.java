package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters;

import com.microsoft.playwright.Playwright;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.AdvancedWebsiteScrapper;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.AdvancedWebsiteScrapperHandler;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.productextractor.ProductExtractor;

public class PlaywrightScrapperHandler implements AdvancedWebsiteScrapperHandler {

    @Override
    public AdvancedWebsiteScrapper getNewScrapperInstance(String url) {
        Playwright playwright = Playwright.create();
        return new PlaywrightScrapper(playwright, new ProductExtractor());
    }
}
