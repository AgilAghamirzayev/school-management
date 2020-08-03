package com.example.project.entity;




import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Course implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String location;


    @ManyToMany(mappedBy = "course",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,  CascadeType.REFRESH})
    private Set<Teacher> teachers;

    @ManyToMany(mappedBy = "course",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,  CascadeType.REFRESH})

    private Set<Student> students;

    public Course(String courseName, String location, Set<Teacher> teachers, Set<Student> students) {
        this.courseName = courseName;
        this.location = location;
        this.teachers = teachers;
        this.students = students;
    }
}
