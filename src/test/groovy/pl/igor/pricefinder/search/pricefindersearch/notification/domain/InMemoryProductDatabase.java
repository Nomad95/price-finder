package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class InMemoryProductDatabase implements ProductsRepository {

    private final Map<Long, Product> products;
    private final AtomicInteger atomicInteger;

    InMemoryProductDatabase() {
        this.atomicInteger = new AtomicInteger(1);
        this.products = new HashMap<>();
    }

    public void add(Long searchId, String productName, BigDecimal maximumPrice) {
        long id = atomicInteger.getAndIncrement();
        products.put( id, new Product( id, searchId, productName, maximumPrice));
    }

    @Value
    private static class Product {
        Long id;
        Long searchId;
        String productName;
        BigDecimal maximumPrice;
    }
}
