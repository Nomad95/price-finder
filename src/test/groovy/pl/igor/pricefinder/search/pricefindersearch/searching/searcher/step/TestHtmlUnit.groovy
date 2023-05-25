package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters.PlaywrightScrapperHandler
import spock.lang.Specification

class TestHtmlUnit extends Specification {

    def "should dupe" () {
        System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "ERROR");
        when:
            def state = new SearchState()
            def site = new SearchGenericSite(state, new PlaywrightScrapperHandler())

            site.executeStep()

        then:
            1==1
    }
}
