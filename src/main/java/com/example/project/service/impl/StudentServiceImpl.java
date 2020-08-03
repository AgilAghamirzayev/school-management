package com.example.project.service.impl;

import com.example.project.entity.Student;
import com.example.project.exception.StudentNotFoundException;
import com.example.project.repository.StudentRepository;
import com.example.project.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateOrSave(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
