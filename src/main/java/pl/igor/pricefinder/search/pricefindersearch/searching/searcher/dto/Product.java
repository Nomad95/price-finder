package pl.igor.pricefinder.search.pricefindersearch.searching.searcher.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Product {
    private String source;
    private String itemId;
    private String name;
    private String brand;
    private String category;
    private String categoryId;
    private Price finalPrice;
    private Price basePrice;
    private String itemUrl;
    private String pictureUrl;
}
