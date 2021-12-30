package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.UserAgents;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.WebsiteScrapper;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.WebsiteSource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Slf4j
public class JsoupScraper implements WebsiteScrapper {

    @Override
    public Optional<WebsiteSource> getByUrl(String url) {
        try {
            Connection.Response resp = Jsoup.connect(url)
                    .timeout(30000)
                    .header("Accept", "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .header("Accept-Language", "pl-PL,pl;q=0.9,en-US;q=0.8,en;q=0.7")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("dnt", "1")
                    .header("Referer", getDomainFromUrl(url))
                    .header("authority", "www.nbsklep.pl")
                    .header("sec-fetch-site", "none")
                    .header("sec-fetch-mode", "navigate")
                    .header("sec-fetch-user", "?1")
                    .header("sec-fetch-dest", "document")
                    .userAgent(UserAgents.I.randomizeUserAgent())
                    .method(Connection.Method.GET)
                    .execute();
            return Optional.of(new JsoupDocument(resp.parse()));
        } catch (IOException e) {
            //TODO: retry template?
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private String getDomainFromUrl(String url) {
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();
            return "https://".concat(domain.startsWith("www.") ? domain.substring(4) : domain);
        } catch (URISyntaxException e) {
            log.error("Could not parse url {}, returning default referer domain of google", url);
            log.error("err: ", e);
            return "https://www.google.com";
        }
    }
}
