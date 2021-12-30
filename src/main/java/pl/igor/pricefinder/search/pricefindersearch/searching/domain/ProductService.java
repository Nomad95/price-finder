package pl.igor.pricefinder.search.pricefindersearch.searching.domain;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ProductService {

    public static final int BATCH_SIZE = 1000;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void saveAll(Collection<Product> products) {
        Iterable<List<Product>> partition = Iterables.partition(products, BATCH_SIZE);
        for (List<Product> productList : partition) {
            productRepository.saveAll(productMapper.mapAll(productList));
        }
    }
}
