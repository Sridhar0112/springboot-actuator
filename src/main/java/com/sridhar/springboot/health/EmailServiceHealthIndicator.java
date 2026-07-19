package com.sridhar.springboot.health;

import jakarta.mail.Transport;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component("emailService")
public class EmailServiceHealthIndicator implements HealthIndicator {

    private final JavaMailSenderImpl mailSender;

    public EmailServiceHealthIndicator(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public Health health() {
        try {
            Transport transport = mailSender.getSession()
                    .getTransport("smtp");
            transport.connect( mailSender.getHost(), mailSender.getPort(), mailSender.getUsername(), mailSender.getPassword());
            transport.close();
            return Health.up()
                    .withDetail("service", "Student Email Service")
                    .withDetail("status", "SMTP Available")
                    .build();

        } catch (Exception e) {
            return Health.down()
                    .withDetail("service", "Student Email Service")
                    .withDetail("status", "SMTP Unavailable")
                    .withException(e)
                    .build();
        }
    }
}