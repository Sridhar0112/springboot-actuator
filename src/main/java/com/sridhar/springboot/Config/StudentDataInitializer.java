package com.sridhar.springboot.Config;

import com.sridhar.springboot.Repository.StudentRepository;
import com.sridhar.springboot.models.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Profile({"dev", "qa", "uat"})
public class StudentDataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final Environment environment;

    public StudentDataInitializer(StudentRepository studentRepository, Environment environment) {
        this.studentRepository = studentRepository;
        this.environment = environment;
    }

    @Override
    public void run(String... args) {
        String activeProfile = String.join(",", environment.getActiveProfiles());
        if (studentRepository.count() > 0) {
            log.info("Student data already exists. Skipping initialization for profile: {}",activeProfile);
            return;
        }
        log.info("Initializing student test data for profile: {}", activeProfile);
        List<String> names = List.of(
                "Jack Wilson", "Tom Anderson", "Bruce Miller", "James Smith", "Oliver Brown",
                "William Davis", "Henry Wilson", "George Thomas", "Harry Martin", "Charlie Taylor",
                "Jacob White", "Noah Harris", "Leo Clark", "Ethan Lewis", "Lucas Walker",
                "Mason Hall", "Logan Allen", "Alexander Young", "Benjamin King", "Daniel Wright",
                "Matthew Scott", "Samuel Green", "Joseph Baker", "David Adams", "Carter Nelson",
                "Owen Hill", "Wyatt Campbell", "John Mitchell", "Jack Roberts", "Luke Carter",
                "Dylan Phillips", "Grayson Evans", "Isaac Turner", "Gabriel Parker", "Anthony Collins",
                "Julian Edwards", "Christopher Stewart", "Andrew Sanchez", "Joshua Morris",
                "Nathan Rogers", "Caleb Reed", "Ryan Cook", "Adrian Morgan", "Jonathan Bell",
                "Thomas Murphy", "Aaron Bailey", "Charles Rivera", "Christian Cooper",
                "Hunter Richardson", "Connor Cox", "Evan Howard", "Robert Ward",
                "Jeremiah Torres", "Nicholas Peterson", "Dominic Gray", "Kevin Ramirez",
                "Jason James", "Brandon Watson", "Tyler Brooks", "Austin Kelly",
                "Jordan Sanders", "Zachary Price", "Jose Bennett", "Ian Wood",
                "Adam Barnes", "Jaxon Ross", "Landon Henderson", "Austin Coleman",
                "Colton Jenkins", "Asher Perry", "Riley Powell", "Carson Long",
                "Leo Patterson", "Xavier Hughes", "Nathaniel Flores", "Kai Washington",
                "Aaron Butler", "Eli Simmons", "Waylon Foster", "Liam Gonzales",
                "Archer Bryant", "Roman Alexander", "Miles Russell", "Vincent Griffin",
                "Maxwell Diaz", "Ryder Hayes", "Sawyer Myers", "Axel Ford",
                "Easton Hamilton", "Maverick Graham", "Silas Sullivan", "Theodore Wallace",
                "Declan Woods", "Emmett Cole", "Finn Jenkins", "Rowan Holmes",
                "Beckett Stone", "Everett Dean", "Micah Spencer", "Blake Porter"
        );

        List<Student> students = getStudents(names);

        studentRepository.saveAll(students);

        log.info("Successfully inserted {} student records", students.size());
    }

    private static List<Student> getStudents(List<String> names) {
        List<String> courses = List.of("JAVA", "SPRING BOOT", "H2", "SERVLET", "JDBC");
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setName(names.get(i));
            student.setCourse(courses.get(i % courses.size()));
            student.setEmail("student" + (i + 1) + "@studentmanagement.com");
            student.setPhone("98765" + String.format("%05d", i + 1));
            students.add(student);
        }
        return students;
    }
}