package com.mmd.restcontrollerdemo.rest;

import com.mmd.restcontrollerdemo.student.StudentErrorResponse;
import com.mmd.restcontrollerdemo.student.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice   //Indicates that this is a global exception handling class
public class StudentGlobalExceptionController {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<StudentErrorResponse> handleStudentNotFoundException(StudentNotFoundException stdEx){
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("MyHeader", "MyHeaderValue")
                .body(new StudentErrorResponse(HttpStatus.NOT_FOUND.value(), stdEx.getMessage(), System.currentTimeMillis()));
    }

    //Handles all possible exceptions.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex){
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new StudentErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad input data", System.currentTimeMillis())
        );
    }
}
