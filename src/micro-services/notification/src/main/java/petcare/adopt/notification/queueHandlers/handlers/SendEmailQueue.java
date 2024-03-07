package petcare.adopt.notification.queueHandlers.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import petcare.adopt.notification.queueHandlers.messageObjects.SendEmail;
import petcare.adopt.notification.services.EmailService;

@Component
public class SendEmailQueue {

    private ObjectMapper objectMapper = new ObjectMapper();
    private EmailService emailService;

    public SendEmailQueue(EmailService emailService) {
        this.emailService = emailService;

    }

    @RabbitListener(queues = "send_email")
    public void updatePetSituation(String message) throws Exception {
        SendEmail emailToSend = objectMapper.readValue(message, SendEmail.class);

        emailService.sendEmail(emailToSend);

    }

}
