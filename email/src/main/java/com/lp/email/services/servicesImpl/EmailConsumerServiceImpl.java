package com.lp.email.services.servicesImpl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.lp.email.config.RabbitConfig;
import com.lp.email.config.config;
import com.lp.email.models.dto.EmailDto;
import com.lp.email.models.dto.ResponseDto;
import com.lp.email.services.EmailConsumerService;

@Component
public class EmailConsumerServiceImpl implements EmailConsumerService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ResponseProducerServiceImpl responseProducerService;

    @RabbitListener(queues = RabbitConfig.EMAIL_QUEUE)
    public void receiveEmail(EmailDto message) {
        System.out.println("Received message: " + message);

        if (config.MOCK_EMAIL) {
            System.out.println("Email sent to: " + message.getEmail());
            ResponseDto response = new ResponseDto(message.getEmail(), message.getSubject(), message.getText(),
                    null);
            responseProducerService.sendResponse(response);
            return;
        }

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(message.getEmail());
            mailMessage.setSubject(message.getSubject());
            mailMessage.setText(message.getText());

            javaMailSender.send(mailMessage);

            ResponseDto response = new ResponseDto(message.getEmail(), message.getSubject(), message.getText(),
                    null);
            responseProducerService.sendResponse(response);

        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
            ResponseDto response = new ResponseDto(message.getEmail(), message.getSubject(), message.getText(),
                    e.getMessage());
            responseProducerService.sendResponse(response);
        }
    }
}