package pl.igor.pricefinder.search.pricefindersearch.notification.dto;

import lombok.Getter;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Getter
public class ProductDto {
    String product;
    BigDecimal finalPrice;
}
