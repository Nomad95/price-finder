package pl.igor.pricefinder.search.pricefindersearch.searching.domain;

import java.util.Collection;

public interface ProductRepository {
    void saveAll(Collection<ProductEntity> products);
}
