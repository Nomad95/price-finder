package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class InMemoryUserRuleDatabase implements UserRuleRepository {

    private final Map<Long, UserRule> userRules;
    private final AtomicInteger atomicInteger;

    InMemoryUserRuleDatabase() {
        this.atomicInteger = new AtomicInteger(1);
        this.userRules = new HashMap<>();
    }

    @Override
    public void saveUserRule(UserRule userRule) {
        long id = atomicInteger.getAndIncrement();
        userRules.put(id, userRule);
    }

    @Override
    public List<UserRule> getRulesByUser(UUID userId) {
        return userRules.values().stream()
                .filter(ur -> ur.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRule> getAllRules() {
        return new ArrayList<>(userRules.values());
    }
}
