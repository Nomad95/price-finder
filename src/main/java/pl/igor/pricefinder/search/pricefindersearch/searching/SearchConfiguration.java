package pl.igor.pricefinder.search.pricefindersearch.searching;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.igor.pricefinder.search.pricefindersearch.searching.infrastructure.*;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.JsoupScraper;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchFactory;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchTask;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.Searcher;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step.StepCreator;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;

@Configuration
public class SearchConfiguration {

    public static final int ONE_SEOND = 1000;

    @Bean
    public TaskScheduler searchWorker(SearchesHolder searchesHolder, TaskWaitingQueue taskWaitingQueue) {
        return new TaskScheduler(searchesHolder, taskWaitingQueue);
    }

    @Bean
    public SearchesHolder searchesHolder(List<Searcher> searchers) {
        return new SearchesHolder(searchers);
    }

    @Bean
    public TaskWaitingQueue taskWaitingQueue() {
        return new TaskWaitingQueue(new LinkedBlockingQueue<>());
    }

    @Bean
    public TaskExecutor taskExecutor(TaskWaitingQueue taskWaitingQueue,
                                     ScheduledExecutorService scheduledExecutorService,
                                     Supplier<TaskExecutionDelayer> taskExecutionDelayerSupplier, OngoingTasks ongoingTasks) {
        return new TaskExecutor(taskWaitingQueue, scheduledExecutorService, taskExecutionDelayerSupplier, ongoingTasks);
    }

    @Bean
    public SearchTaskDelayer searchTaskDelayer() {
        return new FixedSearchTaskDelay(ONE_SEOND);
    }

    @Bean
    public OngoingTasks ongoingTasks(List<SearchTask> ongoingTasks, SearchConfig searchConfig, ApplicationEventPublisher applicationEventPublisher) {
        return new OngoingTasks(ongoingTasks, searchConfig.getMaxOngoingSearches(), applicationEventPublisher);
    }

    @Bean
    public ScheduledExecutorService defaultExecutorService(SearchConfig searchConfig) {
        return Executors.newScheduledThreadPool(searchConfig.getMaxOngoingSearches());
    }

    @Bean
    public Supplier<TaskExecutionDelayer> taskExecutionDelayerSupplier(SearchConfig searchConfig) {
        return () -> searchConfig::getTaskDelayInSeconds;
    }

    @Bean
    @Profile({"!test"})
    public CsvProductsSaver csvProductsSaver() {
        return new CsvProductsSaver("/home/nomad/Repos/pricefinder/price-finder-core/src/testfiles/nb.csv");
    }

    @Bean
    @Profile({"!test"})
    public JpaPostgresProductRepository jpaPostgresProductRepository(JpaProductRepository jpaProductRepository) {
        return new JpaPostgresProductRepository(jpaProductRepository);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository, new ProductMapper());
    }

    @Bean
    @Profile({"!test"})
    public DbProductsSaver dbProductsSaver(ProductService productService) {
        return new DbProductsSaver(productService);
    }

    @Bean
    @Profile({"!test"})
    public SearchIdGenerator searchIdGenerator() {
        return new DbSearchIdGenerator();
    }

    @Bean
    @Profile({"!test"})
    public Searcher newBalanceSiteSearcher(SearchIdGenerator searchIdGenerator, @Qualifier("dbProductsSaver") ProductsSaver productSaver) {
        return SearchFactory.buildSiteSearch().withName("Search All products in New Balance site")
                .addStep(StepCreator.createConfigurationStep(searchIdGenerator))
                .addStep(StepCreator.createNewBalanceFindMenuLinksStep("https://nbsklep.pl/", new JsoupScraper()))
                .addStep(StepCreator.createSplitMenuLinksStep(StepCreator.createSearchNewBalanceUrlStepFactory(new JsoupScraper(), new SpringWebClientProvider())))
                .addStep(StepCreator.createSaveStep(productSaver))
                .withDelayer(searchTaskDelayer())
                .build();
    }

}
