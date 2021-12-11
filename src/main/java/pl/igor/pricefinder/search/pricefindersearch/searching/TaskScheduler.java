package pl.igor.pricefinder.search.pricefindersearch.searching;

import lombok.RequiredArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.Searcher;

import java.util.List;

@RequiredArgsConstructor
public class TaskScheduler {

    private final SearchesHolder searchesHolder;
    private final TaskWaitingQueue taskWaitingQueue;

    public void fetchNewSearches() {
        List<Searcher> searchers = searchesHolder.getAvailableSearches();
        searchers.stream()
                .map(Searcher::createSearchTask)
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
