package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.AdvancedWebsiteScrapper;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.JavascriptFunctions;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.UserAgents;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.productextractor.ProductExtractor;

import java.util.HashMap;
import java.util.List;

@Slf4j
public class PlaywrightScrapper implements AdvancedWebsiteScrapper, AutoCloseable {

    private final Playwright playwright;
    private final Page page;
    private final ProductExtractor productExtractor;

    private String pageHtml = "";

    public PlaywrightScrapper(Playwright playwright, ProductExtractor productExtractor) {
        this.playwright = playwright;
        this.productExtractor = productExtractor;
        BrowserType chromium = playwright.chromium();
        Browser browser = chromium.launch();
        this.page = browser.newPage();
    }

    @Override
    public void connectToUrl(String url) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("authority", url);
        headers.put("method", "GET");
        headers.put("path", "/api/frontend/pl/products/filtered/from_category/13033?withChildCategoryName=13033&withGoogleAnalyticsGtag=true&withVariantsAvailability=true&associations_sas%5B0%5D%5Bpurpose%5D=product_colours&associations_sas[0][filter][onlyVisibleOnListing]=true&page=2&limit=48");
        headers.put("scheme", "https");
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("accept-encoding", "gzip, deflate, br");
        headers.put("accept-language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.put("accept-version", "2");
        headers.put("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MzExMjgxMjQsImp0aSI6IlZobERVMlB2MkRhU05XbGRPMXNMK05Qb2ZXYXlkWWI1UjBmd1wvRFJBTWxrPSIsImlzcyI6Im5ic2tsZXAucGwiLCJleHAiOjE2MzExMjk5MjQsImRhdGEiOnsiY3VzdG9tZXIiOiJhMjk4NWM0Yi1hMzNiLTQ0YmEtYmFkZS04NDQ1NWVlNDEzOWYifX0.3Z706AAaw4HDyflyRyVhIxAu4vAsXny8lnjP2JSCJZf0aZM6W417DOj6j9tVrPAbLDzMGoVFWKkht7NMOeCVwA");
        headers.put("cookie", "_gcl_au=1.1.186566074.1631129024; kg-uuid=f14dcde1-3bd9-49d7-bae7-4fb7abe047e4; _snrs_uuid=14616674-841c-4485-ad37-941b61172954; _snrs_puuid=14616674-841c-4485-ad37-941b61172954; _snrs_sa=ssuid:75ada30b-b064-4198-ac6e-a8313185a2bd&appear:1631129025&sessionVisits:8; _snrs_sb=ssuid:75ada30b-b064-4198-ac6e-a8313185a2bd&leaves:1631129214; _snrs_p=host:nbsklep.pl&permUuid:14616674-841c-4485-ad37-941b61172954&uuid:14616674-841c-4485-ad37-941b61172954&identityHash:&user_hash:&init:1631129025&last:1631129025&current:1631129214&uniqueVisits:1&allVisits:3");
        headers.put("dnt", "1");
        headers.put("is-currency-code", "PLN");
        headers.put("is-delivery-country-code", "pl");
        headers.put("referer", url);//TODO: ????;
        headers.put("sec-ch-ua", "\"Google Chrome\";v=\"93\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"93\"");
        headers.put("sec-ch-ua-mobile", "?0");
        headers.put("sec-ch-ua-platform", "Linux");
        headers.put("sec-fetch-dest", "empty");
        headers.put("sec-fetch-mode", "cors");
        headers.put("sec-fetch-site", "same-origin");
        headers.put("sec-gpc", "1");
        headers.put("user-agent", UserAgents.I.randomizeUserAgent());
        headers.put("x-request-id", "fde4643b-f019-4fca-9efd-712aef6275e2");;

        page.setExtraHTTPHeaders(headers);

        page.navigate(url);
    }

    @Override
    public void scrollToBottom() {
        waitFor(10_000);

        page.evaluate(JavascriptFunctions.SCROLL_TO_BOTTOM);

        waitFor(10_000);
    }

    @Override
    public void snapshotHtml() {
        this.pageHtml = page.content();
    }

    @Override
    public void searchForProducts() {
        List<Product> products = productExtractor.findProductsInHtml(pageHtml);
    }

    @Override
    public List<Product> getFoundProducts() {
        return null;
    }

    @Override
    public void close() {
        try {
            playwright.close();
        } catch (Exception e) {
            log.error("Playwright instance could not be closed", e);
        }
    }

    private void waitFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
