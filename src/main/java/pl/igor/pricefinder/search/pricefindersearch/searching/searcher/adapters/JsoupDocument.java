package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.adapters;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import pl.igor.pricefinder.search.pricefindersearch.searching.searcher.WebsiteSource;

@AllArgsConstructor
public class JsoupDocument implements WebsiteSource {
    
    private final Document document;
    
    @Override
    public CharSequence html() {
        return document.html();
    }

    @Override
    public String getElementHtmlById(String id) {
        return document.getElementById(id).data();
    }
}
