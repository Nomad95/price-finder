package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step


import spock.lang.Specification

import java.time.LocalDate

class ConfigurationStepTest extends Specification {

    def "Should set search id and search date"() {
        given:
            def state = new SearchState()
            def step = new ConfigurationStep(state, () -> 100L)

        when:
            step.executeStep()

        then:
            state.getSearchId() == 100L
            state.getSearchDate() == LocalDate.now()
    }

}
