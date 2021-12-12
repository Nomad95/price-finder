package pl.igor.pricefinder.search.pricefindersearch;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import pl.igor.pricefinder.search.pricefindersearch.searching.ProductsSaver;
import pl.igor.pricefinder.search.pricefindersearch.searching.SearchConfiguration;
import pl.igor.pricefinder.search.pricefindersearch.searching.infrastructure.ProductMapper;
import pl.igor.pricefinder.search.pricefindersearch.searching.infrastructure.ProductRepository;
import pl.igor.pricefinder.search.pricefindersearch.searching.infrastructure.ProductService;

@TestConfiguration
public class TestSearchConfiguration extends SearchConfiguration {

    public static final int ONE_SEOND = 1000;

    @Bean
    public ProductRepository mockRepository() {
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository, new ProductMapper());
    }

    @Bean
    @Primary
    public ProductsSaver productsSaver(ProductService productService) {
        return Mockito.mock(ProductsSaver.class);
    }

}
