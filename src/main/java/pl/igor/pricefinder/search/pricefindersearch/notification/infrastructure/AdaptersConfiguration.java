package pl.igor.pricefinder.search.pricefindersearch.notification.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.ProductsQuery;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserNotificationStrategyQuery;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserRuleRepository;

import javax.persistence.EntityManager;

@Configuration
class AdaptersConfiguration {

    @Bean
    public UserNotificationStrategyQuery tempMailUserNotificationStrategyQuery(JavaMailSender javaMailSender){
        return new TempMailUserNotificationStrategyQuery(javaMailSender);
    }

    @Bean
    @Profile({"!test"})
    public ProductsQuery productsQuery(EntityManager entityManager) {
        return new DbProductsQuery(entityManager);
    }

    @Bean
    @Profile({"!test"})
    public UserRuleRepository userRuleRepository(JpaUserRepository jpaUserRepository) {
        return new DbUserRuleRepositoryAdapter(jpaUserRepository);
    }
}
