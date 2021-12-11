package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step

import org.jsoup.Jsoup
import pl.igor.pricefinder.search.pricefindersearch.searching.JsoupDocument
import pl.igor.pricefinder.search.pricefindersearch.searching.WebsiteScrapper
import spock.lang.Specification

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class SearchMenuLinksTest extends Specification {

    public static final String TEST_MAIN_URL = "https://nbsklep.pl"

    def "should find all links from html"() {
        given:
            def parsedDocument = Jsoup.parse(getClass().getResource("/NewBalanceScript.html").text)
            def state = SearchState.builder().build()
            def jsoupScraperMock = mock(WebsiteScrapper)
            when(jsoupScraperMock.getByUrl(TEST_MAIN_URL))
                    .thenReturn(Optional.of(new JsoupDocument(parsedDocument)))
            def fetchMenuLinksStep = new NewBalanceFindMenuLinks(state, TEST_MAIN_URL, jsoupScraperMock)

        when:
            fetchMenuLinksStep.executeStep()

        then:
            fetchMenuLinksStep.isDone()
            state.fetchLink() == "https://nbsklep.pl/meskie/obuwie/klasyczne"
            state.fetchLink() == "https://nbsklep.pl/meskie/obuwie/made_in_us_and_uk"
            state.fetchLink() == "https://nbsklep.pl/meskie/obuwie/biegowe"
    }
}
