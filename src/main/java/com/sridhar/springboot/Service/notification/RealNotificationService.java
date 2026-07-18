package com.sridhar.springboot.Service.notification;

import com.sridhar.springboot.Service.NotificationService;
import com.sridhar.springboot.logging.util.LoggingMaskUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@Profile({"staging","prod"})
@RequiredArgsConstructor
public class RealNotificationService implements NotificationService {

    private final JavaMailSender mailSender;
    @Override
    public void sendNotification(String message,String email) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email);
            mail.setSubject("Student Created Successfully");
            mail.setText(message);
            mailSender.send(mail);
            log.info("Email notification sent successfully to {}", LoggingMaskUtil.maskEmail(email));
        } catch (MailException exception) {
            log.error("Failed to send email notification to {}. Reason: {}", LoggingMaskUtil.maskEmail(email), exception.getMessage(), exception);
        }
    }
}