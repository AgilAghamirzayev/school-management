package com.example.project.controller;

import com.example.project.entity.Student;
import com.example.project.exception.StudentNotFoundException;
import com.example.project.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("getAllStudents")
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("getStudent/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") Long id) {
        final Student student = studentService.getById(id).orElseThrow(() -> {
            throw new StudentNotFoundException("Student not Found id: " + id);
        });
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("saveStudent")
    public ResponseEntity<Student> save(Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.OK);
    }

    @PostMapping("updateStudent")
    public ResponseEntity<Student> updateOrSave(Student student) {
        return new ResponseEntity<>(studentService.updateOrSave(student), HttpStatus.OK);
    }

    @DeleteMapping("deleteStudent/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        studentService.getById(id).orElseThrow(() -> {
            throw new StudentNotFoundException("Student not Found id: " + id);
        });
        studentService.delete(id);
        return new ResponseEntity<>(String.format("Student successfully deleted by id: %d", id), HttpStatus.OK);
    }
}
