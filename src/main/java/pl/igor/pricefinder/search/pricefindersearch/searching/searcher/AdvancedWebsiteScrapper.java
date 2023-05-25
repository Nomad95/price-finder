package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import java.util.List;

public interface AdvancedWebsiteScrapper {

    void connectToUrl(String url);

    void scrollToBottom();

    void snapshotHtml();

    void searchForProducts();

    List<Product> getFoundProducts();
}
