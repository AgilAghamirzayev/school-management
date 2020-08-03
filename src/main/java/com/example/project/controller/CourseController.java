package com.example.project.controller;

import com.example.project.entity.Course;
import com.example.project.exception.CourseNotFoundException;
import com.example.project.service.CourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("getAllCourses")
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("getCourse/{id}")
    public ResponseEntity<Course> getById(@PathVariable("id") Long id) {

        final Course course = courseService.getById(id).orElseThrow(() -> {
            throw new CourseNotFoundException("Course not Found id: " + id);
        });

        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("saveCourse")
    public ResponseEntity<Course> save(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.save(course), HttpStatus.OK);
    }

    @PutMapping("updateCourse")
    public ResponseEntity<Course> updateOrSave(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.updateOrSave(course), HttpStatus.OK);
    }

    @DeleteMapping("deleteCourse/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        return courseService.getById(id).map(data -> {
                    if (data.getTeachers().isEmpty() && data.getStudents().isEmpty()) {
                        courseService.delete(id);
                        if (courseService.getById(id).isPresent()) {
                            return ResponseEntity.unprocessableEntity().body("Failed to delete the specified course");
                        } else return ResponseEntity.ok().body("Successfully deleted specified course");
                    } else return ResponseEntity.unprocessableEntity().body("Failed to delete,  Please delete the teachers and " +
                                "students associated with this course");
                }
        ).orElseThrow(() -> {
            throw new CourseNotFoundException("Course not Found id: " + id);
        });

    }
}
