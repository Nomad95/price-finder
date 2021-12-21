package pl.igor.pricefinder.search.pricefindersearch.notification.dto;

import lombok.Getter;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Getter
public class RuleDto {
    String product;
    BigDecimal maximumPrice;
}
