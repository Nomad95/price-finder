package pl.igor.pricefinder.search.pricefindersearch.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.NotificationStrategy;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserNotificationStrategyQuery;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.UserNotificationStrategies;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Slf4j
@RequiredArgsConstructor
public class MockNotificationStrategyQuery implements UserNotificationStrategyQuery {

    private final NotificationStrategy notificationStrategy;

    @Override
    public UserNotificationStrategies getUserNotificationStrategies(List<UUID> userIds) {
        Map<UUID, NotificationStrategy> strategies = userIds.stream()
                .collect(toMap(
                        Function.identity(),
                        i -> notificationStrategy));

        return new UserNotificationStrategies(strategies);
    }
}
