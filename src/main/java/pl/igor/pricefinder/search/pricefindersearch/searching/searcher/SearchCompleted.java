package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class SearchCompleted extends ApplicationEvent {
    //TODO: make distinction between searchStep level events and searchTask level???
    @Getter
    Long searchId;

    public SearchCompleted(Object source, Long searchId) {
        super(source);
        this.searchId = searchId;
    }
}
