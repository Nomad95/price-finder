package pl.igor.pricefinder.search.pricefindersearch.notification.domain

import org.mockito.Mockito
import pl.igor.pricefinder.search.pricefindersearch.notification.MockNotificationStrategyQuery
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductDto
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductsForUser
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.RuleDto
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.UserId
import spock.lang.Specification

class NotificationsFacadeTest extends Specification {

    public static final UserId USER_1 = new UserId(UUID.randomUUID())
    public static final UserId USER_2 = new UserId(UUID.randomUUID())

    private NotificationFacade notificationFacade
    private InMemoryProductDatabase products
    private InMemoryUserRuleDatabase userRules
    private NotificationStrategy notificationStrategy;

    def setup() {
        products = new InMemoryProductDatabase()
        userRules = new InMemoryUserRuleDatabase()
        notificationStrategy = Mockito.mock(NotificationStrategy)
        def strategyQuery = new MockNotificationStrategyQuery(notificationStrategy)
        notificationFacade = new NotificationsConfiguration().notificationFacade(strategyQuery, products, userRules)
    }

    def "should add new rule as a user"() {
        given:
            def rule = new RuleDto("New Balance 1080v11", new BigDecimal("250.0"))

        when:
            notificationFacade.addNewRule(rule, USER_1)

        then:
            notificationFacade.findRulesByUser(USER_1).getRules().get(0).getProduct() == "New Balance 1080v11"
            notificationFacade.findRulesByUser(USER_1).getRules().get(0).getMaximumPrice() == new BigDecimal("250.0")
    }

    def "should search for items when new search is completed"() {
        given:
            products.add(1L, "New Balance 1080v11", new BigDecimal("300.00"))
            products.add(1L, "Szczotka do kibla", new BigDecimal("5.00"))
            products.add(1L, "Rekawice bokserskie", new BigDecimal("100.30"))
            products.add(1L, "Stół", new BigDecimal("499.99"))
            products.add(1L, "Wół", new BigDecimal("1300.00"))

            notificationFacade.addNewRule(new RuleDto("New Balance 1080v11", new BigDecimal("250.0")), USER_1)
            notificationFacade.addNewRule(new RuleDto("Stół", new BigDecimal("5000.0")), USER_2)

        when:
            def results = notificationFacade.searchForNotifications(1L)

        then:
            results.getProductsForUser(USER_1.getId()).size() == 0

            results.getProductsForUser(USER_2.getId()).size() == 1
            results.getProductsForUser(USER_2.getId()).get(0).getProduct().contains("Stół")
    }

    def "should find items case insensitive"() {
        given:
            products.add(1L, "New Balance 1080v11", new BigDecimal("300.00"))
            products.add(1L, "Szczotka do kibla", new BigDecimal("5.00"))
            products.add(1L, "Rekawice bokserskie", new BigDecimal("100.30"))
            products.add(1L, "Stół", new BigDecimal("499.99"))
            products.add(1L, "Wół", new BigDecimal("1300.00"))

            notificationFacade.addNewRule(new RuleDto("neW balancE", new BigDecimal("500.0")), USER_1)
            notificationFacade.addNewRule(new RuleDto("sTół", new BigDecimal("5000.0")), USER_2)

        when:
            def results = notificationFacade.searchForNotifications(1L)

        then:
            results.getProductsForUser(USER_1.getId()).size() == 1
            results.getProductsForUser(USER_1.getId()).get(0).getProduct().contains("New Balance")

            results.getProductsForUser(USER_2.getId()).size() == 1
            results.getProductsForUser(USER_2.getId()).get(0).getProduct().contains("Stół")
    }

    //TODO: what about short rules like 'a' or 'ab'...?
    def "should find only items for given search id"() {
        given:
            products.add(2L, "New Balance 1080v11", new BigDecimal("300.00"))
            products.add(1L, "Szczotka do kibla", new BigDecimal("5.00"))
            products.add(1L, "Rekawice bokserskie", new BigDecimal("100.30"))
            products.add(3L, "Stół", new BigDecimal("499.99"))
            products.add(1L, "Wół", new BigDecimal("1300.00"))

            notificationFacade.addNewRule(new RuleDto("New Balance 1080v11", new BigDecimal("500.0")), USER_1)
            notificationFacade.addNewRule(new RuleDto("sTół", new BigDecimal("5000.0")), USER_2)

        when:
            def results = notificationFacade.searchForNotifications(1L)

        then:
            results.getProductsForUser(USER_1.getId()).size() == 0

            results.getProductsForUser(USER_2.getId()).size() == 0
    }

    def "should find multiple items for one rule"() {
        given:
            products.add(1L, "New Balance 1080v11", new BigDecimal("10.00"))
            products.add(1L, "Balanceless handle", new BigDecimal("5.00"))
            products.add(1L, "Super hydrabalance", new BigDecimal("10.30"))
            products.add(1L, "Old balance - mydło", new BigDecimal("10.99"))
            products.add(1L, "My Balance - super orzeszki w czeko", new BigDecimal("10.00"))

            notificationFacade.addNewRule(new RuleDto("Balance", new BigDecimal("500.0")), USER_1)

        when:
            def results = notificationFacade.searchForNotifications(1L)

        then:
            results.getProductsForUser(USER_1.getId()).size() == 5
            results.getProductsForUser(USER_1.getId()).stream()
                    .map(p -> p.getProduct().toLowerCase())
                    .allMatch(p -> p.contains("balance"));
    }

    def "should give empty results when no items were found for rule"() {
        given:
            products.add(1L, "New Balance 1080v11", new BigDecimal("10.00"))
            products.add(1L, "Balanceless handle", new BigDecimal("5.00"))
            products.add(1L, "Super hydrabalance", new BigDecimal("10.30"))
            products.add(1L, "Old balance - mydło", new BigDecimal("10.99"))
            products.add(1L, "My Balance - super orzeszki w czeko", new BigDecimal("10.00"))

            notificationFacade.addNewRule(new RuleDto("superrule123", new BigDecimal("1.0")), USER_1)

        when:
            def results = notificationFacade.searchForNotifications(1L)

        then:
            results.getProductsForUser(USER_1.getId()).size() == 0
    }

    def "should send notifications forall users with appropriate strategy"() {
        given:
            def product1 = new ProductDto("New Balance 1080v11", new BigDecimal("100.00"))
            def product2 = new ProductDto("Żal do włosów", new BigDecimal("10.00"))
            def product3 = new ProductDto("Old balance - mydło", new BigDecimal("5.00"))
            def product4 = new ProductDto("Penceta soniczna", new BigDecimal("1.00"))

            def map = new HashMap<UUID, List<ProductDto>>()

            map.put(USER_1.getId(), List.of(product1, product2))
            map.put(USER_2.getId(), List.of(product3, product4))

            def userToProductsMap = new ProductsForUser(map)

        when:
            notificationFacade.sendNotifications(userToProductsMap)

        then:
            Mockito.verify(notificationStrategy, Mockito.times(1))
                    .sentNotification(List.of(product1, product2))
            Mockito.verify(notificationStrategy, Mockito.times(1))
                    .sentNotification(List.of(product3, product4))
    }

}
