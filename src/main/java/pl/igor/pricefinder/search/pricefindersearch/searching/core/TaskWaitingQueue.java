package pl.igor.pricefinder.search.pricefindersearch.searching.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchTask;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class TaskWaitingQueue {

    private final BlockingQueue<SearchTask> waitingTasks;

    boolean addTask(SearchTask searchTask) {
        return waitingTasks.offer(searchTask);
    }

    NextSearchTask takeTask() {
        try {
            return NextSearchTask.of(waitingTasks.poll(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return NextSearchTask.empty();
    }

    int getTaskCountInQueue() {
        return waitingTasks.size();
    }
}

@AllArgsConstructor(staticName = "of")
class NextSearchTask {
    @Getter(AccessLevel.PACKAGE)
    private SearchTask searchTask;

    static NextSearchTask empty() {
        return NextSearchTask.of(null);
    }

    boolean isNotAvailable() {
        return Objects.isNull(searchTask);
    }
}
