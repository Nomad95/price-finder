package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import lombok.Value;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductDto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class InMemoryProductDatabase implements ProductsQuery {

    private final Map<Long, Product> products;
    private final AtomicInteger atomicInteger;

    InMemoryProductDatabase() {
        this.atomicInteger = new AtomicInteger(1);
        this.products = new HashMap<>();
    }

    public void add(Long searchId, String productName, BigDecimal maximumPrice) {
        long id = atomicInteger.getAndIncrement();
        products.put(id, new Product(id, searchId, productName, maximumPrice));
    }

    @Override
    public List<ProductDto> getProductsByFilter(ProductFilter productFilter) {
        Stream<Product> stream = products.values().stream();

        List<String> toLowerFilterStrings = productFilter.getProductNames().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        if (!productFilter.getProductNames().isEmpty()) {
            stream = stream
                    .filter(p ->
                        toLowerFilterStrings.stream().anyMatch(toLowerFilterString -> p.getProductName().toLowerCase().contains(toLowerFilterString))
                    );
        }

        if (Objects.nonNull(productFilter.getMaxPrice())) {
            stream = stream
                    .filter(p -> p.getMaximumPrice().compareTo(productFilter.getMaxPrice()) <= 0);
        }

        if (Objects.nonNull(productFilter.getSearchId())) {
            stream = stream
                    .filter(p -> p.getSearchId().equals(productFilter.getSearchId()));
        }

        return stream
                .map(p -> new ProductDto(p.getProductName(), p.getMaximumPrice()))
                .collect(Collectors.toList());
    }

    @Value
    private static class Product {
        Long id;
        Long searchId;
        String productName;
        BigDecimal maximumPrice;
    }
}
