package pl.igor.pricefinder.search.pricefindersearch.searching;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchTask;

import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
public class TaskExecutor {

    private static final int ONE_SECOND = 1000;

    private final TaskWaitingQueue taskWaitingQueue;
    private final ScheduledExecutorService scheduledExecutorService;
    private final Supplier<TaskExecutionDelayer> taskDelaySupplier;
    private final OngoingTasks ongoingTasks;

    @Scheduled(fixedDelay = ONE_SECOND)
    void takeTask() {
        log.info("scheduling new task!");
        NextSearchTask nextSearchTask = taskWaitingQueue.takeTask();
        if (nextSearchTask.isNotAvailable()) {
            return;
        }

        if (ongoingTasks.isFull()) {
            return;
        }

        SearchTask searchTask = nextSearchTask.getSearchTask();
        scheduledExecutorService.schedule(searchTask, taskDelaySupplier.get().getNextDelay(), TimeUnit.SECONDS);
        ongoingTasks.add(searchTask);
        log.info("taskScheduled!");
    }

    @Scheduled(fixedDelay = 500)
    void checkOngoingTasks() {
        ongoingTasks.removeFinishedTasks();
        log.info("checkOngoing");
    }

    @PreDestroy
    void endTasks() {
        ongoingTasks.endAllTasks();
    }


}
