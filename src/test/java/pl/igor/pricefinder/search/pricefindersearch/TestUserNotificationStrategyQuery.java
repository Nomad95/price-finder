package pl.igor.pricefinder.search.pricefindersearch;

import pl.igor.pricefinder.search.pricefindersearch.notification.domain.NotificationStrategy;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserNotificationStrategyQuery;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductDto;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.UserNotificationStrategies;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class TestUserNotificationStrategyQuery implements UserNotificationStrategyQuery {

    private TestCaptureNotificationStrategy notificationStrategy;

    public TestUserNotificationStrategyQuery() {
        notificationStrategy = new TestCaptureNotificationStrategy();
    }

    @Override
    public UserNotificationStrategies getUserNotificationStrategies(List<UUID> userIds) {
        Map<UUID, NotificationStrategy> strategies = userIds.stream()
                .collect(toMap(
                        Function.identity(), i -> notificationStrategy));

        return new UserNotificationStrategies(strategies);
    }

    public int getCountOfCapturedNotifications() {
        return notificationStrategy.getCountOfCapturedNotifications();
    }
}

class TestCaptureNotificationStrategy implements NotificationStrategy {

    private int counter;

    @Override
    public void sendNotification(List<ProductDto> products) {
        counter++;
    }

    int getCountOfCapturedNotifications() {
        return counter;
    }
}