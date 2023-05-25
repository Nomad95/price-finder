package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(of = "taskName")
@RequiredArgsConstructor
public class GenericSearcher implements RepeatableSearch {

    private final String taskName;
    private final SitesProvider sitesProvider;
    private final GenericSearchTaskCreator genericSearchTaskCreator;

    @Override
    public SearchTask createSearchTask() {
        return genericSearchTaskCreator.createGenericSearchTask(sitesProvider.getNextSiteToSearch());
    }

}
