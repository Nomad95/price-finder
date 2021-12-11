package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product
import spock.lang.Specification

class AppendStepTest extends Specification {
    static final String TEST_URL_1 = "https://nbsklep.pl/meskie/obuwie/biegowe"
    static final String TEST_URL_2 = "https://nbsklep.pl/damskie/odziez/biustonosze"
    static final String TEST_URL_3 = "https://nbsklep.pl/dzieciece/akcesoria/torby"

    def "should append step in between previous and next"() {
        given:
            def searchState = new SearchState()
            def firstStep = new TestLinkHolderStep(searchState, TEST_URL_1)
            def lastStep = new TestLinkHolderStep(searchState, TEST_URL_3)
            firstStep.appendNextStep(lastStep)

            def middleStep = new TestLinkHolderStep(searchState, TEST_URL_2)

        when:
            firstStep.appendNextStep(middleStep)

        then:
            firstStep.getNextStep() == new TestLinkHolderStep(searchState, TEST_URL_2)
            firstStep.getNextStep().getNextStep() == new TestLinkHolderStep(searchState, TEST_URL_3)
    }

    def "should preserve same searchState between previous and next"() {
        given:
            def searchState = new SearchState()
            addDummyProduct(searchState)

            def firstStep = new TestLinkHolderStep(searchState, TEST_URL_1)
            def lastStep = new TestLinkHolderStep(searchState, TEST_URL_3)
            firstStep.appendNextStep(lastStep)

            def middleStep = new TestLinkHolderStep(searchState, TEST_URL_2)

        when:
            firstStep.appendNextStep(middleStep)

        then:
            firstStep.getProductsSize() == 1
            middleStep.getProductsSize() == 1
            lastStep.getProductsSize() == 1
    }

    private addDummyProduct(SearchState searchState) {
        searchState.addProduct(Product.builder().build())
    }

}
