package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.SearchStep;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import javax.annotation.concurrent.NotThreadSafe;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NotThreadSafe
abstract class AbstractSearchStep implements SearchStep {
    protected @Nullable SearchStep previousStep;
    protected @Nullable SearchStep nextStep;
    private boolean stepDone;

    @Getter(value = AccessLevel.PACKAGE)
    @Setter(value = AccessLevel.PRIVATE)
    private SearchState searchState;

    public AbstractSearchStep(SearchState searchState) {
        this.searchState = searchState;
    }

    @Override
    public boolean hasNextStep() {
        return Objects.nonNull(nextStep);
    }

    @Override //TODO: remove candidate
    public void setNextStep(@NonNull SearchStep step) {
        nextStep = step;
    }

    @Override
    public void setPreviousStep(@NonNull SearchStep step) {
        previousStep = step;
    }

    @Override
    public boolean isDone() {
        return stepDone;
    }

    @Override
    public void appendNextStep(@NonNull SearchStep step) {
        if (hasNextStep()) {
            nextStep.setPreviousStep(step);
            step.setNextStep(nextStep);
        }

        step.setPreviousStep(this);
        setNextStep(step);
        if (nextStep instanceof AbstractSearchStep) {
            ((AbstractSearchStep) nextStep).setSearchState(searchState);
        }
    }

    @Override
    @Nullable
    public SearchStep getNextStep() {
        return nextStep;
    }

    protected void markStepAsDone() {
        stepDone = true;
    }

    protected void setBaseUrl(@NonNull String baseUrl) {
        searchState.setBaseUrl(baseUrl);
    }

    protected void applySiteConfig(SiteConfig siteConfig) {
        searchState.setStartUrl(siteConfig.getSiteStartUrl());
    }

    protected void addLinkToVisit(String link) {
        searchState.addLinkToVisit(link);
    }

    protected String fetchNextLink() {
        return searchState.fetchLink();
    }

    protected boolean hasNextLink() {
        return searchState.hasNextLink();
    }

    protected void addProduct(@NonNull Product product) {
        searchState.addProduct(product);
    }

    protected void addProducts(@NonNull List<Product> products) {
        searchState.addProducts(products);
    }

    protected String getBaseUrl() {
        return searchState.getBaseUrl();
    }

    protected List<Product> getProducts(int batch) {
        if (searchState.getProductsSize() <= batch) {
            return searchState.getAllProducts();
        }
        return searchState.getAllProducts().subList(0, batch);
    }

    protected int getProductsSize() {
        return searchState.getProductsSize();
    }

    protected void removeProducts(List<Product> products) {
        searchState.removeProducts(products);
    }

    protected void setSearchId(Long id) {
        searchState.setSearchId(id);
    }

    protected void setSearchDateAsNow() {
        searchState.setSearchDate(LocalDate.now());
    }

    protected Long getSearchId() {
        return searchState.getSearchId();
    }

    protected LocalDate getSearchDate() {
        return searchState.getSearchDate();
    }

    public void init() {

    }

    @Override
    public Iterable<ApplicationEvent> getAllEvents() {
        return searchState.getApplicationEvents();
    }

    void addEvent(ApplicationEvent applicationEvent) {
        searchState.addEvent(applicationEvent);
    }
}
