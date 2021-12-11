package pl.igor.pricefinder.search.pricefindersearch.searching;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;

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
