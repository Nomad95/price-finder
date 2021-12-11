package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step


import spock.lang.Specification

class SplitSearchLinksTest extends Specification {
    static final String TEST_URL_1 = "https://nbsklep.pl/meskie/obuwie/biegowe"
    static final String TEST_URL_2 = "https://nbsklep.pl/damskie/odziez/biustonosze"
    static final String TEST_URL_3 = "https://nbsklep.pl/dzieciece/akcesoria/torby"

    def "should split links to steps and append it to present step"() {
        given:
            def searchState = new SearchState()
            def addUrlsToSearchStep = addLinkStepWithLinks(searchState, [
                    TEST_URL_1, TEST_URL_2, TEST_URL_3])

        and:
            def splitUrlsToTasksStep = createSplitLinksStep(searchState)
            addUrlsToSearchStep.setNextStep(splitUrlsToTasksStep)

        when:
            splitUrlsToTasksStep.executeStep()

        then:
            splitUrlsToTasksStep.getNextStep() == new TestLinkHolderStep(searchState, TEST_URL_1)
            splitUrlsToTasksStep.getNextStep().getNextStep() == new TestLinkHolderStep(searchState, TEST_URL_2)
            splitUrlsToTasksStep.getNextStep().getNextStep().getNextStep() == new TestLinkHolderStep(searchState, TEST_URL_3)
    }

    def createSplitLinksStep(SearchState state) {
        new SplitSearchLinksStep(state, (url) -> new TestLinkHolderStep(state, url))
    }

    def addLinkStepWithLinks(SearchState state, List<String> urls) {
        def sourceStep = new TestAddLinksStep(state, urls)
        sourceStep.executeStep()
        sourceStep
    }
}
