package pl.igor.pricefinder.search.pricefindersearch;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupZalandoTest {

    public static void main(String[] args) throws IOException {
        String loginUrl = "https://www.google.com/search?q=minecraft+toys&tbm=shop";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
        Connection.Response resp = Jsoup.connect(loginUrl) //
                .timeout(30000) //
                .header("Accept", "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("Accept-Language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("dnt", "1")
                .header("Referer", "http://www.google.com/")
                .header("authority", "www.google.com")
                .header("sec-fetch-site", "none")
                .header("sec-fetch-mode", "navigate")
                .header("sec-fetch-user", "?1")
                .header("sec-fetch-dest", "document")
                .userAgent(userAgent)
                .method(Connection.Method.GET) //
                .execute();

        Document document = resp.parse();

        System.out.println(document);
    }
}
