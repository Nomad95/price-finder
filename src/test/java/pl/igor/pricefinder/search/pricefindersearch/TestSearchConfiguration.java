package pl.igor.pricefinder.search.pricefindersearch;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import pl.igor.pricefinder.search.pricefindersearch.searching.SearchConfiguration;
import pl.igor.pricefinder.search.pricefindersearch.searching.domain.ProductMapper;
import pl.igor.pricefinder.search.pricefindersearch.searching.domain.ProductRepository;
import pl.igor.pricefinder.search.pricefindersearch.searching.domain.ProductService;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.ProductsSaver;

@TestConfiguration
public class TestSearchConfiguration extends SearchConfiguration {

    @Bean
    public ProductRepository mockRepository() {
        return Mockito.mock(ProductRepository.class); //TODO: no test implementation
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository, new ProductMapper());
    }

    @Bean
    @Primary
    public ProductsSaver productsSaver(ProductService productService) {
        return Mockito.mock(ProductsSaver.class);//TODO: no test implementation
    }

    @Bean
    public TestIdGenerator searchIdGenerator() {
        return new TestIdGenerator();
    }
}
