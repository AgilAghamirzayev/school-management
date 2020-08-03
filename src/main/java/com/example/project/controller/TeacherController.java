package com.example.project.controller;

import com.example.project.entity.Teacher;
import com.example.project.exception.TeacherNotFoundException;
import com.example.project.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("getAllTeachers")
    public ResponseEntity<List<Teacher>> getAll() {
        return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
    }

    @GetMapping("getTeacher/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable("id") Long id) {
        final Teacher teacher = teacherService.getById(id).orElseThrow(() -> {
            throw new TeacherNotFoundException("Teacher not Found id: " + id);
        });
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @PostMapping("saveTeacher")
    public ResponseEntity<Teacher> save(Teacher teacher) {
        return new ResponseEntity<>(teacherService.save(teacher), HttpStatus.OK);
    }

    @PostMapping("updateTeacher")
    public ResponseEntity<Teacher> updateOrSave(Teacher teacher) {
        return new ResponseEntity<>(teacherService.updateOrSave(teacher), HttpStatus.OK);
    }

    @DeleteMapping("deleteTeacher/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        teacherService.getById(id).orElseThrow(() -> {
            throw new TeacherNotFoundException("Teacher not Found id: " + id);
        });
        teacherService.delete(id);
        return new ResponseEntity<>(String.format("Teacher successfully deleted id: %d", id), HttpStatus.OK);
    }
}
