package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductsForUser;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.UserNotificationStrategies;

import java.util.List;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
class NotificationService {
    private final UserNotificationStrategyQuery userStrategyQuery;

    void sendNotifications(ProductsForUser productsForUser) {
        List<UUID> usersToNotify = productsForUser.getUsers();
        UserNotificationStrategies userNotificationStrategies = userStrategyQuery.getUserNotificationStrategies(usersToNotify);

        for (UUID userId : usersToNotify) {
            NotificationStrategy strategy = userNotificationStrategies.getForUser(userId);
            strategy.sendNotification(productsForUser.getProductsForUser(userId));
        }

    }
}
