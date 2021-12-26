package pl.igor.pricefinder.search.pricefindersearch.notification.infrastructure;

import lombok.AllArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.NotificationStrategy;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductDto;

import java.util.List;

@AllArgsConstructor
public class MailNotificationStrategy implements NotificationStrategy {
    private final String mail;

    @Override
    public void sentNotification(List<ProductDto> products) {

    }
}
