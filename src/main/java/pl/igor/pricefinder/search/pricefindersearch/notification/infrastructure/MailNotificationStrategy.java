package pl.igor.pricefinder.search.pricefindersearch.notification.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.NotificationStrategy;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductDto;

import java.util.List;

@AllArgsConstructor
public class MailNotificationStrategy implements NotificationStrategy {

    private final String mail;
    private final JavaMailSender emailSender;

    @Override
    public void sendNotification(List<ProductDto> products) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pricefinder.notifications@gmail.com");
        message.setTo(mail);
        message.setSubject("Znaleziono tani produkt!");
        StringBuilder sb = new StringBuilder("Znaleziono nowe produkty godne Twojej uwagi: \n\n");
        products.forEach(product ->
                sb
                        .append(product.getProduct())
                        .append(" - ")
                        .append(product.getFinalPrice())
                        .append(" zł ")
                        .append(" - ")
                        .append(product.getProductLink())
                        .append("\n")
        );
        message.setText(sb.toString());
        emailSender.send(message);
    }
}
