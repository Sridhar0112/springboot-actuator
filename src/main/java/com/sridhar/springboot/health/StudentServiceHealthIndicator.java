package com.sridhar.springboot.health;

import com.sridhar.springboot.Service.StudentService;
import com.sridhar.springboot.health.service.StudentHealthService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceHealthIndicator implements HealthIndicator {

    private final StudentHealthService studentHealthService;

    public StudentServiceHealthIndicator(StudentHealthService studentHealthService) {
        this.studentHealthService = studentHealthService;
    }

    @Override
    public Health health() {
        try {
            long totalStudents = studentHealthService.getStudentCount();
            return Health.up()
                    .withDetail("service", "Student Service")
                    .withDetail("totalStudents", totalStudents)
                    .withDetail("status", "Available")
                    .build();
        }
        catch(Exception ex){
            return Health.down(ex)
                    .withDetail("service", "Student Service")
                    .withDetail("status", "Unavailable")
                    .build();
        }

    }
}
