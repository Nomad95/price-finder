package pl.igor.pricefinder.search.pricefindersearch;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupZalandoNormalneTest {

    public static void main(String[] args) throws IOException {
        String loginUrl = "https://www.zalando.pl/tommy-hilfiger-heritage-crew-neck-graphic-tee-t-shirt-z-nadrukiem-to121d0dn-k11.html";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
        Connection.Response resp = Jsoup.connect(loginUrl) //
                .timeout(30000) //
                .header("Accept", "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("Accept-Language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("dnt", "1")
                .header("Referer", "https://www.zalando.pl/")
                .header("authority", "www.zalando.pl")
                .header("sec-fetch-site", "none")
                .header("sec-fetch-mode", "navigate")
                .header("sec-fetch-user", "?1")
                .header("sec-fetch-dest", "document")
                .userAgent(userAgent)
                .method(Connection.Method.GET)
                .execute();

        Document document = resp.parse();

        System.out.println(document);
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
