package pl.igor.pricefinder.search.pricefindersearch.notification.dto;

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Getter
public class UserRulesDto {
    UUID userId;
    List<RuleDto> rules;

    public List<RuleDto> getRules() {
        return ImmutableList.copyOf(rules);
    }
}
