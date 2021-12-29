package pl.igor.pricefinder.search.pricefindersearch.searching;

import org.springframework.context.ApplicationEventPublisher;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchTask;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.Searcher;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;

public class SearchingTestConfiguration {

    public TaskScheduler searchWorker(SearchesHolder searchesHolder, TaskWaitingQueue taskWaitingQueue) {
        return new TaskScheduler(searchesHolder, taskWaitingQueue);
    }

    public SearchesHolder searchesHolder(List<Searcher> searchers) {
        return new SearchesHolder(searchers);
    }

    public TaskWaitingQueue taskWaitingQueue() {
        return new TaskWaitingQueue(new LinkedBlockingQueue<>());
    }

    public TaskExecutor taskExecutor(TaskWaitingQueue taskWaitingQueue,
                                     ScheduledExecutorService scheduledExecutorService,
                                     Supplier<TaskExecutionDelayer> taskExecutionDelayerSupplier, OngoingTasks ongoingTasks) {
        return new TaskExecutor(taskWaitingQueue, scheduledExecutorService, taskExecutionDelayerSupplier, ongoingTasks);
    }

    public OngoingTasks ongoingTasks(List<SearchTask> ongoingTasks, int capacity, ApplicationEventPublisher applicationEventPublisher) {
        return new OngoingTasks(ongoingTasks, capacity, applicationEventPublisher);
    }
}
