package pl.igor.pricefinder.search.pricefindersearch.notification.application;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.NotificationFacade;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductsForUser;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchCompleted;

@RequiredArgsConstructor
public class SearchCompletedListener {

    private final NotificationFacade notificationFacade;

    @Async
    @EventListener
    public void process(SearchCompleted searchCompleted) {
        ProductsForUser productsForUser = notificationFacade.searchForNotifications(searchCompleted.getSearchId());
        notificationFacade.sendNotifications(productsForUser);
    }
}
