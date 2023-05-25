package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.productextractor


import spock.lang.Specification

class ProductExtractorTest extends Specification {

    String newBalanceSiteHtml
    String zalandoSiteHtml
    String eobuwieSiteHtml
    String googleShoppingSiteHtml

    ProductExtractor extractor

    def setup() {
        newBalanceSiteHtml = getClass().getResource("/NewBalanceRunningMenShoesSite.html").text
        zalandoSiteHtml = getClass().getResource("/ZalandoMenRunningShoesSite.html").text
        eobuwieSiteHtml = getClass().getResource("/EobuwieMenRunningShoesSite.html").text
        googleShoppingSiteHtml = getClass().getResource("/GoogleShoppingRunningMenShoesSite.html").text
    }

    def "should find products on many sites"() {
        given:
            extractor = new ProductExtractor()
            def products = new ArrayList()

        when:
            products.addAll(extractor.findProductsInHtml(newBalanceSiteHtml))
            products.addAll(extractor.findProductsInHtml(zalandoSiteHtml))
            products.addAll(extractor.findProductsInHtml(eobuwieSiteHtml))
            products.addAll(extractor.findProductsInHtml(googleShoppingSiteHtml))

        then:
            products.size() > 0
    }
}
