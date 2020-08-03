package com.example.project.beans;

import com.example.project.entity.Course;
import com.example.project.entity.Student;
import com.example.project.entity.Teacher;
import com.example.project.repository.CourseRepository;
import com.example.project.repository.StudentRepository;
import com.example.project.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

@Component
@AllArgsConstructor
public class AutoRun implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    @Override
    public void run(String... args) {

        final Student student1 = new Student("Ayla", "Hesenli", 20, "ayla@gmail.com", 222222222);
        final Student student2 = new Student("Hesen", "Hesenli", 19, "henli@gmail.com", 999999999);
        final Student student3 = new Student("Ali", "Nesin", 21, "ali@gmail.com", 111111111);


        final Teacher teacher1 = new Teacher("Namik", "Nesin", 21, "nesin@gmail.com", 7777777);
        final Teacher teacher2 = new Teacher("Aysu", "Hesenli", 20, "aysu@gmail.com", 333333);
        final Teacher teacher3 = new Teacher("Ayan", "Hesenli", 19, "henli@gmail.com", 4444444);

//        studentRepository.saveAll(Arrays.asList(
//                new Student("Ali", "Nesin", 21, "ali@gmail.com", 111111111),
//                new Student("Ayla", "Hesenli", 20, "ayla@gmail.com", 222222222),
//                student2
//                ));
//
//        teacherRepository.saveAll(Arrays.asList(
//                new Teacher("Namik", "Nesin", 21, "nesin@gmail.com", 7777777),
//                new Teacher("Aysu", "Hesenli", 20, "aysu@gmail.com", 333333),
//                new Teacher("Ayan", "Hesenli", 19, "henli@gmail.com", 4444444)
//        ));

        courseRepository.saveAll(Arrays.asList(
                new Course("Zeka", "Narimanov", Set.of(teacher1,teacher2,teacher3), Set.of(student1,student2,student3)),
                new Course("Ulduz", "Nəsimi", Set.of(teacher1,teacher2, teacher3), Set.of(student1,student3)),
                new Course("Istek", "Quba", Set.of(teacher3,teacher2), Set.of(student3,student2,student1)),
                new Course("Hedef", "20 Yanvar", Set.of(teacher3), Set.of(student1)),
                new Course("Kaspi", "Genclik", Set.of(teacher1,teacher2), Set.of(student1,student2))

        ));
    }
}
