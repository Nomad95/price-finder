package pl.igor.pricefinder.search.pricefindersearch.notification.dto;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public final class ProductsForUser {

    private final Map<UUID, List<ProductDto>> userIdToProducts;

    public List<ProductDto> getProductsForUser(UUID userId) {
        return new ArrayList<>(userIdToProducts.getOrDefault(userId, new ArrayList<>()));
    }

    public List<UUID> getUsers() {
        return new ArrayList<>(userIdToProducts.keySet());
    }

}
