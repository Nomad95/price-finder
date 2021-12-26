package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import java.util.List;
import java.util.UUID;

public interface UserRuleRepository {

    void saveUserRule(UserRule userRule);

    List<UserRule> getRulesByUser(UUID userId);

    List<UserRule> getAllRules();
}
