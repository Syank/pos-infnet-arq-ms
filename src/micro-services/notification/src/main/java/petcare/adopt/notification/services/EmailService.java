package petcare.adopt.notification.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import petcare.adopt.notification.queueHandlers.messageObjects.SendEmail;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String mailFrom;

    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(SendEmail emailToSend) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(mailFrom);
        helper.setTo(emailToSend.getEmail());
        helper.setSubject(emailToSend.getTitle());
        helper.setText(emailToSend.getBody(), emailToSend.isHtml());

        mailSender.send(message);

    }

}
