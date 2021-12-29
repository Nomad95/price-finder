package pl.igor.pricefinder.search.pricefindersearch;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserNotificationStrategyQuery;
import pl.igor.pricefinder.search.pricefindersearch.searching.SearchCompleted;

@SpringBootTest
@Import({TestSearchConfiguration.class, TestNotificationConfiguration.class})
@ActiveProfiles(profiles = "test")
@TestPropertySource(locations="classpath:application.yml")
public class AsynchronousEventTest {

    @MockBean
    private UserNotificationStrategyQuery testUserNotificationStrategyQuery;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void should() {
        applicationEventPublisher.publishEvent(new SearchCompleted(this, 1L));

        Mockito.verify(testUserNotificationStrategyQuery,
                Mockito.timeout(5000)).getUserNotificationStrategies(Mockito.anyList());

    }

}
