package com.example.project.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandling extends ResponseEntityExceptionHandler {

    private static final HttpStatus INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionHandling> handleAllException(Exception e, WebRequest request) {
        final ExceptionHandling exceptionHandling = new ExceptionHandling(new Date(),
                INTERNAL_SERVER_ERROR.name(), INTERNAL_SERVER_ERROR.value(),
                e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionHandling, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public final ResponseEntity<ExceptionHandling> handleCourseNotFoundException(Exception e, WebRequest request) {
        final ExceptionHandling exceptionHandling = new ExceptionHandling(new Date(), NOT_FOUND.name(),
                NOT_FOUND.value(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionHandling, NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public final ResponseEntity<ExceptionHandling> handleStudentNotFoundException(Exception e, WebRequest request) {
        final ExceptionHandling exceptionHandling = new ExceptionHandling(new Date(), NOT_FOUND.name(),
                NOT_FOUND.value(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionHandling, NOT_FOUND);
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public final ResponseEntity<ExceptionHandling> handleTeacherNotFoundException(Exception e, WebRequest request) {
        final ExceptionHandling exceptionHandling = new ExceptionHandling(new Date(), NOT_FOUND.name(),
                NOT_FOUND.value(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionHandling, NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        final ExceptionHandling exceptionHandling = new ExceptionHandling(new Date(), BAD_REQUEST.name(),
                BAD_REQUEST.value(), ex.getMessage(), ex.getBindingResult().toString());
        return new ResponseEntity<>(exceptionHandling, BAD_REQUEST);
    }
}
