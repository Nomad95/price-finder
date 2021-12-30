package pl.igor.pricefinder.search.pricefindersearch.tests_poc;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupSkapiecTest {

    public static void main(String[] args) throws IOException {
        String loginUrl = "https://www.skapiec.pl/site/red/9652/874348880/157206268";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
        Connection.Response resp = Jsoup.connect(loginUrl) //
                .followRedirects(true)
                .timeout(30000) //
                .header("Accept", "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("Accept-Language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("dnt", "1")
                .header("Referer", "https://www.skapiec.pl/")
                .header("authority", "www.skapiec.pl")
                .header("sec-fetch-site", "none")
                .header("sec-fetch-mode", "navigate")
                .header("sec-fetch-user", "?1")
                .header("sec-fetch-dest", "document")
                .userAgent(userAgent)
                .method(Connection.Method.GET) //
                .execute();
        System.out.println(resp.url().toExternalForm());
        Document document = resp.parse();

        System.out.println(document);

//        String postUrl = "https://www.skapiec.pl/get-offer-url/9652/874348880/157206268/1";
//        Connection.Response resp2 = Jsoup.connect(postUrl) //
//                .timeout(30000) //
//                .header("Accept", "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
//                .header("Accept-Language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7")
//                .header("Accept-Encoding", "gzip, deflate, br")
//                .header("dnt", "1")
//                .header("Referer", "https://www.skapiec.pl/")
//                .header("authority", "www.skapiec.pl")
//                .header("sec-fetch-site", "none")
//                .header("sec-fetch-mode", "navigate")
//                .header("sec-fetch-user", "?1")
//                .header("sec-fetch-dest", "document")
//                .userAgent(userAgent)
//                .method(Connection.Method.POST) //
//                .execute();

        //:authority: www.skapiec.pl
        //:method: POST
        //:path: /get-offer-url/9652/874348880/157206268/1
        //:scheme: https
        //accept:*/*
        //accept-encoding:gzip, deflate, br
        //accept-language:pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7
        //content-length:8
        //content-type:application/x-www-form-urlencoded
        //dnt:1
        //origin:https://www.skapiec.pl
        //referer:https://www.skapiec.pl/site/red/9652/874348880/157206268
        //sec-ch-ua:"Google Chrome";v="93", " Not;A Brand";v="99", "Chromium";v="93"
        //sec-ch-ua-mobile:?0
        //sec-ch-ua-platform:"Linux"
        //sec-fetch-dest:empty
        //sec-fetch-mode:cors
        //sec-fetch-site:same-origin
        //sec-gpc:1
        //user-agent:Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36
        //x-requested-with:XMLHttpRequest

    }//
}
