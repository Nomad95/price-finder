package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import pl.igor.pricefinder.search.pricefindersearch.notification.dto.RuleDto;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.UserId;

class Creator {

    UserRule createUserRule(RuleDto ruleDto, UserId userId) {
        return UserRule.builder()
                .userId(userId.getId())
                .product(ruleDto.getProduct())
                .maximumPrice(ruleDto.getMaximumPrice())
                .build();
    }
}
