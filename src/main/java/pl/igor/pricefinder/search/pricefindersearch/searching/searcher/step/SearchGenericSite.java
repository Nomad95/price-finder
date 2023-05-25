package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.AdvancedWebsiteScrapper;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.AdvancedWebsiteScrapperHandler;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import java.util.List;

@Slf4j
class SearchGenericSite extends AbstractSearchStep {

    private final AdvancedWebsiteScrapperHandler advancedWebsiteScrapper;

    SearchGenericSite(@NonNull SearchState searchState, AdvancedWebsiteScrapperHandler advancedWebsiteScrapper) {
        super(searchState);
        this.advancedWebsiteScrapper = advancedWebsiteScrapper;
    }

    @Override
    public void init() {
        //TODO: test difference
    }

    @Override
    public void executeStep() throws Exception {
        final String url = fetchNextLink();

        AdvancedWebsiteScrapper site = advancedWebsiteScrapper.getNewScrapperInstance(url);
        site.scrollToBottom();
        site.scrollToBottom();
        site.scrollToBottom();
        site.snapshotHtml();
        site.searchForProducts();
        List<Product> products = site.getFoundProducts();

        addProducts(products);
    }
}
