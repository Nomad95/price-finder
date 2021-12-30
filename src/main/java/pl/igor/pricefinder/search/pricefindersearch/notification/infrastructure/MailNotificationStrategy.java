package pl.igor.pricefinder.search.pricefindersearch.notification.infrastructure;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import pl.igor.pricefinder.search.pricefindersearch.notification.domain.NotificationStrategy;
import pl.igor.pricefinder.search.pricefindersearch.notification.dto.ProductDto;

import javax.mail.internet.MimeMessage;
import java.util.List;

@AllArgsConstructor
public class MailNotificationStrategy implements NotificationStrategy {

    private final String mail;
    private final JavaMailSender emailSender;

    @Override
    @SneakyThrows
    public void sendNotification(List<ProductDto> products) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("pricefinder.notifications@gmail.com");
        mimeMessageHelper.setTo(mail);
        mimeMessageHelper.setSubject("Znaleziono tani produkt!");
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
        mimeMessageHelper.setText(sb.toString());
        emailSender.send(mimeMessage);
    }
}
