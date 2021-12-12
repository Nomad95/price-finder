package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import java.util.List;
import java.util.Queue;

@Builder
@Getter(value = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class SearchState {
    @Setter(value = AccessLevel.PACKAGE)
    private String startUrl;

    @Setter(value = AccessLevel.PACKAGE)
    @Getter(value = AccessLevel.PACKAGE)
    private String baseUrl;

    @Builder.Default
    @Getter(value = AccessLevel.NONE)
    private Queue<String> linksToVisit = Queues.newLinkedBlockingDeque(); //TODO: max links

    @Builder.Default
    @Getter(value = AccessLevel.NONE)
    private List<Product> products = Lists.newArrayList(); //TODO: max  products

    void addLinkToVisit(String link) {
        linksToVisit.offer(link);
    }

    String fetchLink() {
        return linksToVisit.poll();
    }

    boolean hasNextLink() {
        return !linksToVisit.isEmpty();
    }

    void addProduct(Product product) {
        products.add(product);
    }

    List<Product> getAllProducts() {
        return List.copyOf(products);
    }

    int getProductsSize() {
        return products.size();
    }

    void removeProducts(List<Product> products) {
        this.products.removeAll(products);
    }
}

@Builder
@Getter
@Setter(value = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class Selectors {
    String menuSelector;
}