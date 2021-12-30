package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.WebsiteScrapper;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.WebsiteSource;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
class NewBalanceFindMenuLinks extends AbstractSearchStep {
    public static final String REGEX = "^\\w+/\\w+/\\w+$";

    private final String mainUrl;
    private final WebsiteScrapper jsoupScraper;

    NewBalanceFindMenuLinks(@NonNull SearchState searchState, @NonNull String mainUrl, WebsiteScrapper jsoupScraper) {
        super(searchState);
        this.mainUrl = mainUrl;
        this.jsoupScraper = jsoupScraper;
    }

    @Override
    public void init() {
        setBaseUrl(mainUrl);
    }

    @Override
    public void executeStep() throws Exception {
        log.info("Executing search new balance menu links");
        Optional<WebsiteSource> documentOpt = jsoupScraper.getByUrl(mainUrl);

        if (documentOpt.isEmpty()) {
            log.info("Could not load menu links document for url {}", mainUrl);
            markStepAsDone();
            return;
        }

//        Elements elementsByTag = document.body().getElementsByTag("app-menu-item-newbalance");
        WebsiteSource document = documentOpt.get();
        String js = document.getElementHtmlById("newbalance-state");
        Pattern pattern = Pattern.compile("niceUrl&q;:&q;(.*?)&q;,&q;");
        Matcher matcher = pattern.matcher(js);
        ArrayList<String> urls = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (group.matches(REGEX) && group.contains("obuwie")) {//TODO: filter
                urls.add(String.format("%s/%s", mainUrl, group));
            }
        }

        urls.forEach(this::addLinkToVisit);
        markStepAsDone();
        log.info("Finished executing search new balance menu links");
    }
}
