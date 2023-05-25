package pl.igor.pricefinder.search.pricefindersearch.searching;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.*;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters.JsoupScraper;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters.SpringWebClientProvider;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step.StepCreator;

@Configuration
public class SearchersConfiguration {

    @Bean
    @Profile({"!test"})
    public RepeatableSearch newBalanceSiteSearcher(SearchIdGenerator searchIdGenerator, @Qualifier("dbProductsSaver") ProductsSaver productSaver, SearchTaskDelayer searchTaskDelayer) {
        return SearchFactory.buildSiteSearcher().withName("Search All products in New Balance site")
                .addStep(StepCreator.createConfigurationStep(searchIdGenerator))
                .addStep(StepCreator.createNewBalanceFindMenuLinksStep("https://nbsklep.pl/", new JsoupScraper()))
                .addStep(StepCreator.createSplitMenuLinksStep(StepCreator.createSearchNewBalanceUrlStepFactory(new JsoupScraper(), new SpringWebClientProvider())))
                .addStep(StepCreator.createSaveStep(productSaver))
                .withDelayer(searchTaskDelayer)
                .build();
    }

    @Bean
    @Profile({"!test"}) //todo: devdb
    public RepeatableSearch genericSearcher(GenericSearchTaskCreator genericSearchTaskCreator, SitesProvider sitesProvider) {
        return new GenericSearcher("Generic search for any site", sitesProvider, genericSearchTaskCreator);
    }

    @Bean
    @Profile({"!test"})
    public GenericSearchTaskCreator genericSearchTaskCreator(SearchIdGenerator searchIdGenerator, @Qualifier("dbProductsSaver") ProductsSaver productSaver, SearchTaskDelayer searchTaskDelayer) {
        return new GenericSearchTaskCreator(searchIdGenerator, productSaver, searchTaskDelayer);
    }
}
