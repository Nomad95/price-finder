package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import pl.igor.pricefinder.search.pricefindersearch.searching.ProductsSaver;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;

import java.util.List;

@Slf4j
public class SaveProducts extends AbstractSearchStep {
    private final ProductsSaver productsSaver;

    public SaveProducts(@NonNull SearchState searchState, ProductsSaver productsSaver) {
        super(searchState);
        this.productsSaver = productsSaver;
    }

    @Override
    public void executeStep() throws Exception {
        log.info("Saving products batch");
        if (getProductsSize() >= 10_000) {
            log.info("Splitting save task. Too many products to save in one batch: {}", getProductsSize());
            appendNextStep(new SaveProducts(getSearchState(), productsSaver));
        }

        List<Product> products = getProducts(10_000);

        productsSaver.saveBatch(products);

        removeProducts(products);

        markStepAsDone();
        log.info("Saved products batch");
    }

}
