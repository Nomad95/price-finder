package pl.igor.pricefinder.search.pricefindersearch.notification.application;

import lombok.RequiredArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.NotificationFacade;
import pl.igor.pricefinder.search.pricefindersearch.searching.SearchCompleted;

@RequiredArgsConstructor
public class SearchCompletedListener {

    private final NotificationFacade notificationFacade;

    public void process(SearchCompleted searchCompleted) {

    }
}
