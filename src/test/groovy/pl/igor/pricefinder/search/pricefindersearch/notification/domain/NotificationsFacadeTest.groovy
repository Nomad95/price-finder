package pl.igor.pricefinder.search.pricefindersearch.notification.domain


import pl.igor.pricefinder.search.pricefindersearch.notification.dto.RuleDto
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.UserId
import spock.lang.Specification

class NotificationsFacadeTest extends Specification {

    public static final UserId USER_1 = new UserId(UUID.randomUUID())
    public static final UserId USER_2 = new UserId(UUID.randomUUID())

    private NotificationFacade notificationFacade
    private InMemoryProductDatabase products

    def setup() {
        products = new InMemoryProductDatabase()
        notificationFacade = new NotificationsConfiguration().notificationFacade(products)
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
            results != null
    }

    //get site searched event and process
    //search products by all users, gather info about who to notify about what product
}
