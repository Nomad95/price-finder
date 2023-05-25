package pl.igor.pricefinder.search.pricefindersearch.searching.core;

import lombok.RequiredArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.RepeatableSearch;

import java.util.List;

@RequiredArgsConstructor
public class TaskScheduler {

    private final SearchesHolder searchesHolder;
    private final TaskWaitingQueue taskWaitingQueue;

    public void fetchNewSearches() {
        List<RepeatableSearch> repeatableSearches = searchesHolder.getAvailableSearchers();
        repeatableSearches.stream()
                .map(RepeatableSearch::createSearchTask)
                .forEach(taskWaitingQueue::addTask);

        //executor class with adding tasks? has holded tasks etc - albo niech tylko executoruje a tutaj niech pobiera i dodaje do executora jesli trzeba. tak :)
        //dobra tutaj niech pobiera i dodaje do executorow, tutaj tez info o current i pending
        //get sites to search (searchers to run)
        //run all workers each started with different time
        //send info to thread watcher
        //trzeba bedzie mozliwosc doawania taskow on the run zrobic np takich z wynikow z googla

        //trzeba gdzies zapisac wyniki

    }


}
