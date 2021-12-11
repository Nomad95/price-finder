package pl.igor.pricefinder.search.pricefindersearch.searching;

import lombok.RequiredArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchTask;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OngoingTasks {
    private final List<SearchTask> ongoingTasks;
    private final int capacity;

    synchronized void endAllTasks() {
        ongoingTasks.forEach(SearchTask::stopExecution);
    }

    synchronized boolean isFull() {
        return ongoingTasks.size() == capacity;
    }

    synchronized void add(SearchTask task) {
        if (isFull()) {
            throw new IllegalStateException("OngoingTasks queue is full");
        }

        ongoingTasks.add(task);
    }

    synchronized void removeFinishedTasks() {
        List<SearchTask> finishedTasks = ongoingTasks.stream()
                .filter(SearchTask::isFinished)
                .collect(Collectors.toList());

        ongoingTasks.removeAll(finishedTasks);
    }

    synchronized int ongoingTasksCount() {
        return ongoingTasks.size();
    }

    public TasksSummary tasksSummary() {
        return new TasksSummary(ongoingTasksCount());
    }
}
