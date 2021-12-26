package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder(access = AccessLevel.PACKAGE)
public class ProductFilter {
    private final Long searchId;
    private final BigDecimal maxPrice;
    private final List<String> productNames;

    public List<String> getProductNames() {
        return List.copyOf(productNames);
    }

    public String getPipeSeparatedProductNames() {
        return productNames.stream()
                .map(String::trim)
                .collect(Collectors.joining("|"));
    }
}
