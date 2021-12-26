package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductDto;

import java.util.List;

public interface ProductsQuery {

    List<ProductDto> getProductsByFilter(ProductFilter productFilter);
}
