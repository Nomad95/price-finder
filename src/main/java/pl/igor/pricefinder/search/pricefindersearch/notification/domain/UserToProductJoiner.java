package pl.igor.pricefinder.search.pricefindersearch.notification.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductDto;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductsForUser;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class UserToProductJoiner {

    private final List<ProductDto> products;
    private final List<UserRule> userRules;

    ProductsForUser joinUsersToProducts() {
        Map<UUID, List<UserRule>> userRulesByUserId = userRules.stream().collect(Collectors.groupingBy(UserRule::getUserId));
        Map<UUID, List<ProductDto>> productsPerUser = userRulesByUserId.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e ->
                        e.getValue().stream()
                                .map(userRule -> products.stream()
                                        .filter(productDto -> productDto.getProduct().toLowerCase().contains(userRule.getProduct().toLowerCase()))
                                        .filter(productDto -> productDto.getFinalPrice().compareTo(userRule.getMaximumPrice()) <= 0)
                                        .collect(Collectors.toSet())
                                )
                                .flatMap(Set::stream)
                                .collect(Collectors.toList())
                ));

        return new ProductsForUser(productsPerUser);
    }

}
