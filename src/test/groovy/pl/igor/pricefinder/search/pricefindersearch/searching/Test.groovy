package pl.igor.pricefinder.search.pricefindersearch.searching

import org.assertj.core.util.Lists
import spock.lang.Specification

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import static org.awaitility.Awaitility.await

class Test extends Specification {

    public static final int MAX_ONGOING_TASK_COUNT = 4

    SearchesHolder searchesHolder
    TaskScheduler searchWorker
    TaskExecutor taskExecutor
    TaskWaitingQueue taskWaitingQueue
    OngoingTasks ongoingTasks

    def setup() {
        def configuration = new SearchingTestConfiguration()
        taskWaitingQueue = configuration.taskWaitingQueue()
        searchesHolder = configuration.searchesHolder(Lists.newArrayList())
        searchWorker = configuration.searchWorker(searchesHolder, taskWaitingQueue)
        ongoingTasks = configuration.ongoingTasks(Lists.newArrayList(), MAX_ONGOING_TASK_COUNT)
        taskExecutor = configuration.taskExecutor(taskWaitingQueue, Executors.newScheduledThreadPool(4),
                new NoDelayTaskExecutionDelayer(), ongoingTasks)
    }

    def "Scheduler should be able to add tasks to task queue"() {
        given:
            availableSearchers(3)

        when: "Worker fetches all searches"
            searchWorker.fetchNewSearches()

        and: "Task executor schedules three tasks to run"
            scheduleSearches(3)

        then: "Three search tasks are running"
            ongoingTasks.tasksSummary().ongoingTasksCount == 3
    }

    def "Ongoing tasks count should not exceed certain number"() {
        given:
            availableSearchers(10)

        when: "Worker fetches all searches"
            searchWorker.fetchNewSearches()

        and: "Task executor schedules all tasks to run"
            scheduleSearches(10)

        then: "Ongoing tasks are not exceeding defined capacity"
            ongoingTasks.tasksSummary().ongoingTasksCount == MAX_ONGOING_TASK_COUNT
    }

    def "Finished searches should be removed from search queue"() {
        given:
            availableSearchers(10)

        when: "Worker fetches all searches"
            searchWorker.fetchNewSearches()

        and: "Task executor schedules all tasks to run"
            scheduleSearches(10)

        then: "Tasks completed after some time"
            await().atMost(5, TimeUnit.SECONDS).until {
                simulateWorkingTaskExecutor()
                taskWaitingQueue.getTaskCountInQueue() == 0
                ongoingTasks.tasksSummary().ongoingTasksCount == 0
            }
    }

    def availableSearchers(int searchersCount) {
        for (i in 0..<searchersCount) {
            searchesHolder.addSearcher(TestSearcherFactory.createNewDefaultTestSearcher())
        }
    }

    def scheduleSearches(int searchesCount) {
        for (i in 0..<searchesCount) {
            taskExecutor.takeTask()
        }
    }

    private void simulateWorkingTaskExecutor() {
        taskExecutor.takeTask()
        taskExecutor.checkOngoingTasks()
    }

}
