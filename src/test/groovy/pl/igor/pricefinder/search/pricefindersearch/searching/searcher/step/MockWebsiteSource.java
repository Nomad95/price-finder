package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.step;

import lombok.AllArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.searching.WebsiteSource;

import java.util.Optional;

@AllArgsConstructor
public class MockWebsiteSource implements WebsiteSource {

    private String websiteSourceString;

    @Override
    public CharSequence html() {
        return websiteSourceString;
    }

    @Override
    public String getElementHtmlById(String id) {
        return null;
    }

    static Optional<MockWebsiteSource> ofStringSource(String source) {
        return Optional.of(new MockWebsiteSource(source));
    }
}
