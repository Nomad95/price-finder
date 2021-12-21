package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NotificationsConfiguration {

    NotificationFacade notificationFacade(ProductsRepository productsRepository) {
        return new NotificationFacade(new NotificationService(productsRepository));
    }

    @Bean
    NotificationFacade notificationFacade(NotificationService notificationService) {
        return new NotificationFacade(notificationService);
    }

    @Bean
    NotificationService notificationService(ProductsRepository productsRepository) {
        return new NotificationService(productsRepository);
    }
}
