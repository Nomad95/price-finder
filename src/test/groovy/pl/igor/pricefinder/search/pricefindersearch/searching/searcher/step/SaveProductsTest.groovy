package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step

import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.ProductsSaver
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product
import spock.lang.Specification

class SaveProductsTest extends Specification {

    def "should split saving for two batches when products list have more than 10_000 entries"() {
        given:
            def searchState = new SearchState()

            for (i in 0..<10_010) {
                searchState.addProduct(Product.builder().itemId(String.valueOf(i)).build())
            }

            def saveProductsStep = new SaveProducts(searchState, new PrintSaver());

        when:
            saveProductsStep.executeStep()

        then:
            saveProductsStep.getNextStep() != null
    }

    def "should save and delete all products that was saved"() {
        given:
            def searchState = new SearchState()

            for (i in 0..<10_010) {
                searchState.addProduct(Product.builder().itemId(String.valueOf(i)).build())
            }

            def saveProductsStep = new SaveProducts(searchState, new PrintSaver());

        when:
            saveProductsStep.executeStep()

        then: "10 left for other task to save"
            searchState.getProductsSize() == 10
    }
}

class PrintSaver implements ProductsSaver {

    @Override
    void saveBatch(List<Product> products) {
        println("saving " + properties.size() + " items")
    }

    @Override
    void save(Product product) {

    }
}
