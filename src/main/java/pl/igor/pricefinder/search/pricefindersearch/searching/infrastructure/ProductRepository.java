package pl.igor.pricefinder.search.pricefindersearch.searching.infrastructure;

import java.util.Collection;

public interface ProductRepository {
    void saveAll(Collection<ProductEntity> products);
}
