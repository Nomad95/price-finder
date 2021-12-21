package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import pl.igor.pricefinder.search.pricefindersearch.searching.WebClientProvider;
import pl.igor.pricefinder.search.pricefindersearch.searching.WebsiteScrapper;
import pl.igor.pricefinder.search.pricefindersearch.searching.WebsiteSource;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.UserAgents;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Price;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class NewBalanceSearchUrl extends AbstractSearchStep {
    private static final int PAGE_LIMIT = 48;

    private final WebsiteScrapper scrapper;
    private final WebClientProvider webClientProvider;
    private final String url;

    public NewBalanceSearchUrl(@NonNull SearchState searchState,
                               @NonNull WebsiteScrapper jsoupScraper, @NonNull WebClientProvider webClientProvider, @NonNull String url) {
        super(searchState);
        this.scrapper = jsoupScraper;
        this.webClientProvider = webClientProvider;
        this.url = url;
    }

    @Override
    public void executeStep() throws Exception {
        log.info("Searching url {}", url);
        //TODO: narazie dla NB - trzeba wylyslec sposob na obsluge roznych stronek

        Optional<WebsiteSource> documentOpt = scrapper.getByUrl(url);

        if (documentOpt.isEmpty()) {
            log.info("Could not get document for url {}. Skipping searching this url", url);
            markStepAsDone();//TODO: mark as failure and reason
            return;
        }

        WebsiteSource document = documentOpt.get();
        Optional<String> categoryIdOptional = findCategoryId(document);
        if (categoryIdOptional.isPresent()) {
            extractProducts(categoryIdOptional.get(), 1);
        } else {
            log.info("Cant find categoryId - no products will be fetched");
        }

        markStepAsDone();
        log.info("Finished searching url {}", url);
    }

    private Optional<String> findCategoryId(WebsiteSource document) {
        Pattern pattern = Pattern.compile("seo\\/category\\/\\d+");
        Matcher matcher = pattern.matcher(document.html());
        if (matcher.find()) {
            String group = matcher.group();
            String categoryId = group.substring(group.lastIndexOf('/') + 1);
            return Optional.of(categoryId);
        }

        return Optional.empty();
    }

    private void extractProducts(String categoryId, int page) {
        WebClient client = webClientProvider.getWithBaseUrl(getBaseUrl().concat("/api/frontend/pl/products/filtered/from_category"));

        String uriString = String.format("/%s?withChildCategoryName=%s&withGoogleAnalyticsGtag=true&withVariantsAvailability=true&page=%s&limit=%s",
                categoryId, categoryId, page, PAGE_LIMIT);

        WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = client.get();
        WebClient.RequestHeadersSpec<?> uri = requestHeadersUriSpec.uri(uriString)
                .header("authority", "nbsklep.pl")
                .header("method", "GET")
                .header("path", "/api/frontend/pl/products/filtered/from_category/13033?withChildCategoryName=13033&withGoogleAnalyticsGtag=true&withVariantsAvailability=true&associations_sas%5B0%5D%5Bpurpose%5D=product_colours&associations_sas[0][filter][onlyVisibleOnListing]=true&page=2&limit=48")
                .header("scheme", "https")
                .header("accept", "application/json, text/plain, */*")
                .header("accept-encoding", "gzip, deflate, br")
                .header("accept-language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("accept-version", "2")
                .header("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MzExMjgxMjQsImp0aSI6IlZobERVMlB2MkRhU05XbGRPMXNMK05Qb2ZXYXlkWWI1UjBmd1wvRFJBTWxrPSIsImlzcyI6Im5ic2tsZXAucGwiLCJleHAiOjE2MzExMjk5MjQsImRhdGEiOnsiY3VzdG9tZXIiOiJhMjk4NWM0Yi1hMzNiLTQ0YmEtYmFkZS04NDQ1NWVlNDEzOWYifX0.3Z706AAaw4HDyflyRyVhIxAu4vAsXny8lnjP2JSCJZf0aZM6W417DOj6j9tVrPAbLDzMGoVFWKkht7NMOeCVwA")
                .header("cookie", "_gcl_au=1.1.186566074.1631129024; kg-uuid=f14dcde1-3bd9-49d7-bae7-4fb7abe047e4; _snrs_uuid=14616674-841c-4485-ad37-941b61172954; _snrs_puuid=14616674-841c-4485-ad37-941b61172954; _snrs_sa=ssuid:75ada30b-b064-4198-ac6e-a8313185a2bd&appear:1631129025&sessionVisits:8; _snrs_sb=ssuid:75ada30b-b064-4198-ac6e-a8313185a2bd&leaves:1631129214; _snrs_p=host:nbsklep.pl&permUuid:14616674-841c-4485-ad37-941b61172954&uuid:14616674-841c-4485-ad37-941b61172954&identityHash:&user_hash:&init:1631129025&last:1631129025&current:1631129214&uniqueVisits:1&allVisits:3")
                .header("dnt", "1")
                .header("is-currency-code", "PLN")
                .header("is-delivery-country-code", "pl")
                .header("referer", "https://nbsklep.pl/meskie/obuwie/biegowe?page=" + 1)//TODO: ????
                .header("sec-ch-ua", "\"Google Chrome\";v=\"93\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"93\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "Linux")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-origin")
                .header("sec-gpc", "1")
                .header("user-agent", UserAgents.I.randomizeUserAgent())
                .header("x-request-id", "fde4643b-f019-4fca-9efd-712aef6275e2");

        Mono<String> rspns = uri.exchangeToMono(response -> {
            if (response.statusCode()
                    .equals(HttpStatus.OK)) {
                return response.bodyToMono(String.class);
            } else if (response.statusCode()
                    .is4xxClientError()) {
                return Mono.just("Error response");
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });

        String json = rspns.block();
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

        JsonArray items = jsonObject.getAsJsonArray("items");

        for (JsonElement item : items) {
            JsonObject itemObject = item.getAsJsonObject();
            Product product = Product.builder()
                    .searchId(getSearchId())
                    .searchDate(getSearchDate())
                    .source("New Balance")
                    .itemId(itemObject.get("id").getAsString())
                    .name(itemObject.get("name").getAsString())
                    .itemUrl(getBaseUrl().concat("/") + itemObject.get("niceUrl").getAsString())
                    .pictureUrl(itemObject.get("mainPictureUrl").getAsString().replace("{imageSafeUri}", "picture/fit-in/400x360/filters:fill(white)"))
                    .basePrice(Price.builder()
                            .gross(new BigDecimal(itemObject.getAsJsonObject("prices").getAsJsonObject("basePrice").get("gross").getAsString()))
                            .currency(itemObject.getAsJsonObject("prices").getAsJsonObject("basePrice").get("currency").getAsString())
                            .build())
                    .finalPrice(Price.builder()
                            .gross(new BigDecimal(itemObject.getAsJsonObject("prices").getAsJsonObject("sellPrice").get("gross").getAsString()))
                            .currency(itemObject.getAsJsonObject("prices").getAsJsonObject("sellPrice").get("currency").getAsString())
                            .build())
                    .brand("New Balance")
                    .category(itemObject.getAsJsonObject("mainCategory").get("name").getAsString())
                    .categoryId(itemObject.getAsJsonObject("mainCategory").get("id").getAsString())
                    .build();
            addProduct(product);
        }

        int currentPage = jsonObject.getAsJsonObject("pagination").get("currentPage").getAsInt();
        int lastPage = jsonObject.getAsJsonObject("pagination").get("lastPage").getAsInt();

        if (currentPage < lastPage) {
            extractProducts(categoryId, ++currentPage);
        }
    }

}
