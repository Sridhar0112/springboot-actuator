package com.sridhar.springboot.Service.notification;

import com.sridhar.springboot.Service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile({"dev", "qa", "uat"})
public class MockNotificationService implements NotificationService {


    @Override
    public void sendNotification(String message, String email) {
        log.info("Mock notification sent to {}: {}", email, message);
    }
}
