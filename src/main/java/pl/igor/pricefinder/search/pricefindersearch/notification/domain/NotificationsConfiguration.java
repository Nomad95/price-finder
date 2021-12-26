package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NotificationsConfiguration {

    NotificationFacade notificationFacade(UserNotificationStrategyQuery userNotifQuery, ProductsQuery productsRepository, UserRuleRepository userRuleRepository) {
        return new NotificationFacade(new NotificationService(userNotifQuery), userRuleRepository, productsRepository, new Creator());
    }

    @Bean
    NotificationFacade notificationFacade(NotificationService notificationService, UserRuleRepository userRuleRepository, ProductsQuery productsQuery) {
        return new NotificationFacade(notificationService, userRuleRepository, productsQuery, new Creator());
    }

    @Bean
    NotificationService notificationService(UserNotificationStrategyQuery userNotifQuery) {
        return new NotificationService(userNotifQuery);
    }
}
