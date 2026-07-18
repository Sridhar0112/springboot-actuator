package com.sridhar.springboot.Service;

import com.sridhar.springboot.Dto.StudentDto;
import com.sridhar.springboot.Exception.StudentException;
import com.sridhar.springboot.Repository.StudentRepository;
import com.sridhar.springboot.logging.util.LoggingMaskUtil;
import com.sridhar.springboot.models.Student;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final NotificationService notificationService;

    public StudentService(StudentRepository studentRepository,NotificationService notificationService){
        this.studentRepository = studentRepository;
        this.notificationService=notificationService;
    }

    public Student AddStudent(StudentDto.StudentRequest student){
        log.info("Student creation started. {}", LoggingMaskUtil.mask(student));
        if(student==null || student.getCourse()==null || student.getName()==null){
            log.warn("Student creation failed. Mandatory fields missing");
            throw new StudentException("Student details cannot be empty");
        }
        Student s= new Student();
        s.setCourse(student.getCourse());
        s.setID(student.getID());
        s.setName(student.getName());
        s.setEmail(student.getEmail());
        s.setPhone(student.getPhone());
        Student savedstudent= studentRepository.save(s);
        notificationService.sendNotification(
                "Hello " + savedstudent.getName() + ",\n\n"
                        + "Your student account has been created successfully.\n"
                        + "Student ID: " + savedstudent.getID()
                        + "\n\nThank you.",
                savedstudent.getEmail()
        );
        log.info("Student created successfully. {}", LoggingMaskUtil.mask(savedstudent));
        return savedstudent;
    }

    public List<Student> getStudents(){
        try{
            log.info("Fetching all students");
            return studentRepository.findAll();
        }
        catch(Exception e){
            throw new StudentException(e.getMessage());
        }
    }

    public Student getStudentsById(long id) {
        return studentRepository.findById(id).orElseThrow(()-> new StudentException("Student not found for Id: "+ id));
    }

    public void deleteStudentById(Long id) {
        Student student=getStudentsById(id);
        studentRepository.delete(student);
    }

    public Student updateStudent(
            Long id,
            StudentDto.StudentUpdateRequest request
    ) {

        if(isEmpty(request)){
            throw new StudentException(
                    "At least one field is required for update"
            );
        }

        Student student = getStudentsById(id);

        // Email update check
        if(request.getEmail() != null
                && !student.getEmail().equals(request.getEmail())) {
            if(studentRepository.existsByEmail(request.getEmail())) {
                throw new StudentException(
                        "Email already exists"
                );
            }
            student.setEmail(request.getEmail());
        }
        // Phone update check
        if(request.getPhone() != null
                && !student.getPhone().equals(request.getPhone())) {
            if(studentRepository.existsByPhone(request.getPhone())) {
                throw new StudentException(
                        "Phone number already exists"
                );
            }
            student.setPhone(request.getPhone());
        }
        // Normal fields
        if(request.getName() != null){
            student.setName(request.getName());
        }
        if(request.getCourse() != null){
            student.setCourse(request.getCourse());
        }
        return studentRepository.save(student);
    }

    public boolean isEmpty(StudentDto.StudentUpdateRequest request){
        return request.getName() == null
                && request.getCourse() == null
                && request.getEmail() == null
                && request.getPhone() == null;
    }
}