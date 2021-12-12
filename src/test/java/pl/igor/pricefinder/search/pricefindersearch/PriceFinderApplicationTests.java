package pl.igor.pricefinder.search.pricefindersearch;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@Import(TestSearchConfiguration.class)
@ActiveProfiles(profiles = "test")
@TestPropertySource(locations="classpath:application.yml")
class PriceFinderApplicationTests {

    @Test
    void contextLoads() {
    }

}
