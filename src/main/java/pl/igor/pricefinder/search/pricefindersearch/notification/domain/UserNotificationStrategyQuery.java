package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import pl.igor.pricefinder.search.pricefindersearch.notification.dto.UserNotificationStrategies;

import java.util.List;
import java.util.UUID;

public interface UserNotificationStrategyQuery {

    UserNotificationStrategies getUserNotificationStrategies(List<UUID> userIds);

}
