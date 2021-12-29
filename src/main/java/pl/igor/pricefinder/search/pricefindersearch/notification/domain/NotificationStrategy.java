package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductDto;

import java.util.List;

public interface NotificationStrategy {
    void sendNotification(List<ProductDto> products);
}
