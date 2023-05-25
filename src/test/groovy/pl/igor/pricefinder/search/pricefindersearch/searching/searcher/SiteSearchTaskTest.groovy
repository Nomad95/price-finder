package pl.igor.pricefinder.search.pricefindersearch.searching.searcher

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step.EmptyStep
import spock.lang.Specification

class SiteSearchTaskTest extends Specification {

    def "Search task should invoke all steps"() {
        def task1 = new EmptyStep(1)
        def task2 = new EmptyStep(2)
        def task3 = new EmptyStep(3)
        def task4 = new EmptyStep(4)

        given:
            def siteSearchTask = SearchFactory.buildSiteSearcher()
                    .withName("Test Search")
                    .addStep(task1)
                    .addStep(task2)
                    .addStep(task3)
                    .addStep(task4)
                    .build()

            def task = siteSearchTask.createSearchTask()

        when:
            task.call()

        then:
            task.isFinished()
            task1.isDone()
            task2.isDone()
            task3.isDone()
            task4.isDone()
    }
}
