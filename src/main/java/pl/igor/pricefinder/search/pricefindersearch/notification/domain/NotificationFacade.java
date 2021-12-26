package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
public class NotificationFacade {

    private final NotificationService notificationService;
    private final UserRuleRepository userRuleRepository;
    private final ProductsQuery productsRepository;
    private final Creator creator;

    public void addNewRule(@NonNull RuleDto ruleDto, @NonNull UserId userId) {
        UserRule userRule = creator.createUserRule(ruleDto, userId);
        userRuleRepository.saveUserRule(userRule);
    }

    public UserRulesDto findRulesByUser(@NonNull UserId userId) {
        List<UserRule> rulesByUser = userRuleRepository.getRulesByUser(userId.getId());
        List<RuleDto> userRules = rulesByUser.stream()
                .map(userRule -> new RuleDto(userRule.getProduct(), userRule.getMaximumPrice()))
                .collect(Collectors.toList());

        return new UserRulesDto(userId.getId(), userRules);
    }

    public ProductsForUser searchForNotifications(@NonNull Long searchId) {
        List<UserRule> allUserRules = userRuleRepository.getAllRules();

        BigDecimal maxRulePrice = allUserRules.stream()
                .map(UserRule::getMaximumPrice)
                .max(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        List<String> products = allUserRules.stream()
                .map(UserRule::getProduct)
                .collect(Collectors.toList());

        ProductFilter productFilter = ProductFilter.builder()
                .productNames(products)
                .maxPrice(maxRulePrice)
                .searchId(searchId)
                .build();

        List<ProductDto> productsByFilter = productsRepository.getProductsByFilter(productFilter);

        return new UserToProductJoiner(productsByFilter, allUserRules).joinUsersToProducts();
    }

    public void sendNotifications(@NonNull ProductsForUser productsForUser) {
        notificationService.sendNotifications(productsForUser);
    }
}
