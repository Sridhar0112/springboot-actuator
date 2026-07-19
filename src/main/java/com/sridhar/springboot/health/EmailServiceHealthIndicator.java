package com.sridhar.springboot.health;

import com.sridhar.springboot.Service.StudentService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("emailService")
public class EmailServiceHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        boolean emailServiceAvailable = true;

        if(emailServiceAvailable) {
            return Health.up()
                    .withDetail("service", "Student Email Service")
                    .withDetail("status", "Available")
                    .build();
        }
        return Health.down()
                .withDetail("service", "Student Email Service")
                .withDetail("status", "Unavailable")
                .build();
    }
}
