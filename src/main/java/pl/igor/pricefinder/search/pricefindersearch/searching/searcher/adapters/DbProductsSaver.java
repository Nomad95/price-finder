package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters;

import lombok.RequiredArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.searching.domain.ProductService;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.ProductsSaver;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import java.util.List;

@RequiredArgsConstructor
public class DbProductsSaver implements ProductsSaver {

    private final ProductService productService;

    @Override
    public void saveBatch(List<Product> products) {
        productService.saveAll(products);
    }

    @Override
    public void save(Product product) {

    }

}
