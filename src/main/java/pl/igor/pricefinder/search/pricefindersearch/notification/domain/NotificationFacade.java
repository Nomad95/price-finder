package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import lombok.RequiredArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.RuleDto;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.UserId;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.UserRulesDto;

@RequiredArgsConstructor
public class NotificationFacade {

    private final NotificationService notificationService;

    public void addNewRule(RuleDto ruleDto, UserId userId) {

    }

    public UserRulesDto findRulesByUser(UserId userId) {
        return null;
    }

    public Object searchForNotifications(Long searchId) {
        //get users
        //get rules
        //get products by rules
        //check them
        //get eliglibe for notification
        return null;
    }
}
