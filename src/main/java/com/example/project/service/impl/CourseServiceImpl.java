package com.example.project.service.impl;

import com.example.project.entity.Course;
import com.example.project.exception.CourseNotFoundException;
import com.example.project.repository.CourseRepository;
import com.example.project.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateOrSave(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
