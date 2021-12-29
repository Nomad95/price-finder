package pl.igor.pricefinder.search.pricefindersearch;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.ProductsQuery;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserRuleRepository;
import pl.igor.pricefinder.search.pricefindersearch.searching.SearchConfiguration;

import javax.persistence.EntityManager;

@TestConfiguration
public class TestNotificationConfiguration extends SearchConfiguration {

    @Bean
    @Primary
    public UserRuleRepository userRuleRepository() {
        return Mockito.mock(UserRuleRepository.class); //TODO: no test implementation
    }

    @Bean
    public EntityManager entityManager() {
        return Mockito.mock(EntityManager.class); //TODO: not in tests
    }

    @Bean
    public ProductsQuery productsQuery() {
        return Mockito.mock(ProductsQuery.class); //TODO: no test implementation
    }


}
