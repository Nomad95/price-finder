package pl.igor.pricefinder.search.pricefindersearch.notification.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import pl.igor.pricefinder.search.pricefindersearch.notification.application.SearchCompletedListener;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.NotificationFacade;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.ProductsQuery;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserNotificationStrategyQuery;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.UserRuleRepository;

import javax.persistence.EntityManager;
import java.util.Properties;

@Configuration
class AdaptersConfiguration {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("pricefinder.notifications@gmail.com");
        mailSender.setPassword("M$PY49zCHr6Kr$&1sTMdwR2%");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public UserNotificationStrategyQuery tempMailUserNotificationStrategyQuery(JavaMailSender javaMailSender){
        return new TempMailUserNotificationStrategyQuery(javaMailSender);
    }

    @Bean
    @Profile({"!test"})
    public ProductsQuery productsQuery(EntityManager entityManager) {
        return new DbProductsQuery(entityManager);
    }

    @Bean
    @Profile({"!test"})
    public UserRuleRepository userRuleRepository(JpaUserRepository jpaUserRepository) {
        return new DbUserRuleRepositoryAdapter(jpaUserRepository);
    }

    @Bean
    public SearchCompletedListener searchCompletedListener(NotificationFacade notificationFacade) {
        return new SearchCompletedListener(notificationFacade);
    }
}
