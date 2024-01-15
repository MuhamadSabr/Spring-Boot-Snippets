package com.mmd.restcontrollerdemo.rest;

import com.mmd.restcontrollerdemo.student.Student;
import com.mmd.restcontrollerdemo.student.StudentErrorResponse;
import com.mmd.restcontrollerdemo.student.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class StudentAPI {
    static int id=1;
    List<Student> students = new ArrayList<>();
    @PostConstruct
    public void createStudents(){
        Random random = new Random();
        String[] studentNames = {"Mohammed", "Ahmed", "Karim", "Amir", "Ali", "Alan", "Omar"};
        for (int i=0; i<10; i++){
            students.add(new Student(id++, studentNames[random.nextInt(7)],
                    studentNames[random.nextInt(7)], random.nextBoolean()));
        }
    }
    @GetMapping("api/students")
    public List<Student> getStudents(){
        return students;
    }
    @GetMapping("api/students/{studentID}")
    public Student getStudent(@PathVariable int studentID){
        for (Student student : students){
            if(student.id() == studentID){
                return student;
            }
        }
        throw new StudentNotFoundException("Student ID not found - " + studentID); //Throw it in step 3. Handle it below, step 4.
    }
}
