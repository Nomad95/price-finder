package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import java.util.List;

public interface ProductsSaver {
    void saveBatch(List<Product> products);
    void save(Product product);
}
