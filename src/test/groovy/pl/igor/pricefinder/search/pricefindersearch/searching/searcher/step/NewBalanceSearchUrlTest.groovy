package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import pl.igor.pricefinder.search.pricefindersearch.searching.SpringWebClientProvider
import pl.igor.pricefinder.search.pricefindersearch.searching.WebsiteScrapper
import spock.lang.Specification

class NewBalanceSearchUrlTest extends Specification {

    private static MockWebServer mockNbApi
    private static WebsiteScrapper scrapper

    def setup() {
        scrapper = Mock(WebsiteScrapper)
        mockNbApi = new MockWebServer()
        mockNbApi.start()
    }

    def cleanup() {
        mockNbApi.shutdown()
    }

    def "Should search products and add them to result list"() {
        given:
            mockNextRequestReturnsTwoProducts()
            def testUrl = String.format("http://localhost:%s", mockNbApi.getPort())

            def searchState = new SearchState()
            searchState.setBaseUrl(testUrl)

            scrapper.getByUrl(_ as String) >> MockWebsiteSource.ofStringSource("l},&q;pl/seo/category/13043&q;:{")

            def searchUrlTask = new NewBalanceSearchUrl(searchState, scrapper, new SpringWebClientProvider(), testUrl)

        when:
            searchUrlTask.executeStep()

        then:
            searchState.getAllProducts().size() == 2
            searchState.getAllProducts().get(0).getItemId() == "3206190"
            searchState.getAllProducts().get(0).getName() == "New Balance MS327LY1"
            searchState.getAllProducts().get(0).getFinalPrice().getGross() == new BigDecimal("399.99")
            searchState.getAllProducts().get(0).getFinalPrice().getCurrency() == "PLN"
            searchState.getAllProducts().get(0).getItemUrl() == String.format("http://localhost:%s/new_balance_ms327ly1", mockNbApi.getPort())
            searchState.getAllProducts().get(0).getPictureUrl() == "https://nbsklep.pl/picture/fit-in/400x360/filters:fill(white)/5c3d321db02193aa79a7ece3d824ca3d"
    }

    def "Should not add products when category is not found"() {
        given:
            mockNextRequestReturnsTwoProducts()
            def testUrl = String.format("http://localhost:%s", mockNbApi.getPort())

            def searchState = new SearchState()
            searchState.setBaseUrl(testUrl)

            scrapper.getByUrl(_ as String) >> MockWebsiteSource.ofStringSource("")

            def searchUrlTask = new NewBalanceSearchUrl(searchState, scrapper, new SpringWebClientProvider(), testUrl)

        when:
            searchUrlTask.executeStep()

        then:
            searchState.getAllProducts().size() == 0
    }

    def mockNextRequestReturnsTwoProducts() {
        def jsonBody = getClass().getResource("/newBalanceApiShoesData.json").text
        mockNbApi.enqueue(new MockResponse().setBody(jsonBody).addHeader("Content-type", "application/json"))
    }

}
