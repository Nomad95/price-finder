package pl.igor.pricefinder.search.pricefindersearch.notification.dto;

import lombok.Value;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.NotificationStrategy;

import java.util.Map;
import java.util.UUID;

@Value
public class UserNotificationStrategies {
    Map<UUID, NotificationStrategy> strategies;

    public NotificationStrategy getForUser(UUID userId) {
        return strategies.get(userId);
    }
}
