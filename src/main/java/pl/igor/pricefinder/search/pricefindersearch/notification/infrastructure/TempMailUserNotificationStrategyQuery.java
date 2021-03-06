package pl.igor.pricefinder.search.pricefindersearch.notification.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.NotificationStrategy;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserNotificationStrategyQuery;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.UserNotificationStrategies;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public class TempMailUserNotificationStrategyQuery implements UserNotificationStrategyQuery {

    private final JavaMailSender javaMailSender;

    @Override
    public UserNotificationStrategies getUserNotificationStrategies(List<UUID> userIds) {
        Map<UUID, NotificationStrategy> strategies = userIds.stream()
                .collect(toMap(
                        Function.identity(),
                        i -> new MailNotificationStrategy("igokop9999@gmail.com", javaMailSender)));

        return new UserNotificationStrategies(strategies);
    }
}
