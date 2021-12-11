package pl.igor.pricefinder.search.pricefindersearch;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Price;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto.Product;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsoupNewBalanceTest {

    public static final String REGEX = "^\\w+/\\w+/\\w+$";
    public static final String BASE_URL = "https://nbsklep.pl/";

    public static void main(String[] args) throws IOException {
        String loginUrl = "https://nbsklep.pl/meskie/obuwie/klasyczne";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
        Connection.Response resp = Jsoup.connect(loginUrl) //
                .timeout(30000) //
                .header("Accept", "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("Accept-Language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("dnt", "1")
                .header("Referer", "https://www.nbsklep.pl/")
                .header("authority", "www.nbsklep.pl")
                .header("sec-fetch-site", "none")
                .header("sec-fetch-mode", "navigate")
                .header("sec-fetch-user", "?1")
                .header("sec-fetch-dest", "document")
                .userAgent(userAgent)
                .method(Connection.Method.GET)
                .execute();

        Document document = resp.parse();

        System.out.println(document);

        Pattern pattern = Pattern.compile("seo\\/category\\/\\d+");
        Matcher matcher = pattern.matcher(document.html());
        if (matcher.find()) {
            String group = matcher.group();
            String caegoryId = group.substring(group.lastIndexOf('/') + 1 );
            System.out.println(caegoryId);
        }

        Optional<String> categoryIdOptional = Optional.of("13043");
        if (categoryIdOptional.isPresent()) {
            List<Product> products = new ArrayList<>();
            exractProducts(categoryIdOptional.get(), products, 1);

            products.forEach(System.out::println);

//        Elements elementsByTag = document.body().getElementsByTag("app-menu-item-newbalance");
//        Elements byClass = document.body().getElementsByClass("ng-tns-c75-2");
//        String js = document.getElementById("newbalance-state").data();
//        Pattern pattern = Pattern.compile("niceUrl&q;:&q;(.*?)&q;,&q;");
//        Matcher matcher = pattern.matcher(js);
//        ArrayList<String> urls = new ArrayList<>();
//        while (matcher.find()) {
//            String group = matcher.group(1);
//            if (group.matches(REGEX)) {
//                urls.add(String.format("%s/%s", loginUrl, group));
//            }
//        }
//
//        System.out.println(urls.toString());

            //System.out.println(js);
            //scrape for category id
            //np: https://nbsklep.pl/api/frontend/pl/products/filtered/from_category/13033?withChildCategoryName=13033&withGoogleAnalyticsGtag=true&withVariantsAvailability=true&associations_sas%5B0%5D%5Bpurpose%5D=product_colours&associations_sas[0][filter][onlyVisibleOnListing]=true&page=2&limit=48
            //potem: https://nbsklep.pl/api/frontend/pl/product_show/3205190?forCategory=newbalance&output=html


            //:authority: nbsklep.pl
            //:method: GET
            //:path: /api/frontend/pl/products/filtered/from_category/13033?withChildCategoryName=13033&withGoogleAnalyticsGtag=true&withVariantsAvailability=true&associations_sas%5B0%5D%5Bpurpose%5D=product_colours&associations_sas[0][filter][onlyVisibleOnListing]=true&page=2&limit=48
            //:scheme: https
            //accept:application/json, text/plain, */*
            //accept-encoding:gzip, deflate, br
            //accept-language:pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7
            //accept-version:2
            //authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MzExMjgxMjQsImp0aSI6IlZobERVMlB2MkRhU05XbGRPMXNMK05Qb2ZXYXlkWWI1UjBmd1wvRFJBTWxrPSIsImlzcyI6Im5ic2tsZXAucGwiLCJleHAiOjE2MzExMjk5MjQsImRhdGEiOnsiY3VzdG9tZXIiOiJhMjk4NWM0Yi1hMzNiLTQ0YmEtYmFkZS04NDQ1NWVlNDEzOWYifX0.3Z706AAaw4HDyflyRyVhIxAu4vAsXny8lnjP2JSCJZf0aZM6W417DOj6j9tVrPAbLDzMGoVFWKkht7NMOeCVwA
            //cookie:_gcl_au=1.1.186566074.1631129024; kg-uuid=f14dcde1-3bd9-49d7-bae7-4fb7abe047e4; _snrs_uuid=14616674-841c-4485-ad37-941b61172954; _snrs_puuid=14616674-841c-4485-ad37-941b61172954; _snrs_sa=ssuid:75ada30b-b064-4198-ac6e-a8313185a2bd&appear:1631129025&sessionVisits:8; _snrs_sb=ssuid:75ada30b-b064-4198-ac6e-a8313185a2bd&leaves:1631129214; _snrs_p=host:nbsklep.pl&permUuid:14616674-841c-4485-ad37-941b61172954&uuid:14616674-841c-4485-ad37-941b61172954&identityHash:&user_hash:&init:1631129025&last:1631129025&current:1631129214&uniqueVisits:1&allVisits:3
            //dnt:1
            //is-currency-code:PLN
            //is-delivery-country-code:pl
            //referer:https://nbsklep.pl/meskie/obuwie/biegowe?page=2
            //sec-ch-ua:"Google Chrome";v="93", " Not;A Brand";v="99", "Chromium";v="93"
            //sec-ch-ua-mobile:?0
            //sec-ch-ua-platform:"Linux"
            //sec-fetch-dest:empty
            //sec-fetch-mode:cors
            //sec-fetch-site:same-origin
            //sec-gpc:1
            //user-agent:Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36
            //x-request-id:fde4643b-f019-4fca-9efd-712aef6275e2
        }
    }

    private static void exractProducts(String categoryId, List<Product> products, int page) {
        WebClient client = WebClient.builder()
                .baseUrl("https://nbsklep.pl/api/frontend/pl/products/filtered/from_category")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(configurer -> {
                    configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024);
                }).build())
                .build();

        String uriString = String.format("/%s?withChildCategoryName=%s&withGoogleAnalyticsGtag=true&withVariantsAvailability=true&page=%s&limit=%s",
                categoryId, categoryId, page, 2);

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
                .header("referer", "https://nbsklep.pl/meskie/obuwie/biegowe?page=" + 1)
                .header("sec-ch-ua", "\"Google Chrome\";v=\"93\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"93\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "Linux")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-origin")
                .header("sec-gpc", "1")
                .header("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36")
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
                    .itemId(itemObject.get("id").getAsString())
                    .name(itemObject.get("name").getAsString())
                    .itemUrl(BASE_URL + itemObject.get("niceUrl").getAsString())
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
            products.add(product);
        }

        int currentPage = jsonObject.getAsJsonObject("pagination").get("currentPage").getAsInt();
        int lastPage = jsonObject.getAsJsonObject("pagination").get("lastPage").getAsInt();

        if (currentPage < lastPage) {
            exractProducts(categoryId, products, ++currentPage);
        }
    }
}