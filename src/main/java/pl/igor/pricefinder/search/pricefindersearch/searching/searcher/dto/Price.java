package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Price {
    private BigDecimal gross;
    private String currency;
}
