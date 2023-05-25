package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import lombok.RequiredArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters.JsoupScraper;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters.SpringWebClientProvider;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step.StepCreator;

@RequiredArgsConstructor
public class GenericSearchTaskCreator {

    private final SearchIdGenerator searchIdGenerator;
    private final ProductsSaver productsSaver;
    private final SearchTaskDelayer searchTaskDelayer;

    SearchTask createGenericSearchTask(String siteUrl) {
        return SearchFactory.buildSiteSearchTask().withName(String.format("Generic search of %s", siteUrl))
                .addStep(StepCreator.createConfigurationStep(searchIdGenerator, siteUrl))
                .addStep(StepCreator.createNewBalanceFindMenuLinksStep("https://nbsklep.pl/", new JsoupScraper()))
                .addStep(StepCreator.createSplitMenuLinksStep(StepCreator.createSearchNewBalanceUrlStepFactory(new JsoupScraper(), new SpringWebClientProvider())))
                .addStep(StepCreator.createSaveStep(productsSaver))
                .withDelayer(searchTaskDelayer)
                .build();
    }
}
