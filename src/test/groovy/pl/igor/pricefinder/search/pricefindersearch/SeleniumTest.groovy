package pl.igor.pricefinder.search.pricefindersearch

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters.SeleniumExample
import spock.lang.Specification

@SpringBootTest
@Import([TestSearchConfiguration.class, TestNotificationConfiguration.class])
@ActiveProfiles(profiles = "test")
@TestPropertySource(locations = "classpath:application.yml")
class SeleniumTest extends Specification {

    def "test"() {
        when:
            def example = new SeleniumExample()
            example.getAboutBaeldungPage()
            def title = example.getTitle()
        then:
            title == "About Baeldung | Baeldung"
    }

}
