package com.kadConsulting.studentManagement.excetpion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.lang.System.currentTimeMillis;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/*
 * Created By
 * @author Aime D. Kodjane
 */
@ControllerAdvice
public class StudentErrorExceptionHandler {
    @ExceptionHandler
    private ResponseEntity<StudentErrorResponse> handleResponse(StudentNotExistException studentNotExistException) {
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setMessage(studentNotExistException.getMessage());
        studentErrorResponse.setStatus(NOT_FOUND.value());
        studentErrorResponse.setTimeStamp(currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse, NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<StudentErrorResponse> handleResponse(BadRequestException exception) {
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setMessage(exception.getMessage());
        studentErrorResponse.setStatus(BAD_REQUEST.value());
        studentErrorResponse.setTimeStamp(currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse, BAD_REQUEST);
    }
}
