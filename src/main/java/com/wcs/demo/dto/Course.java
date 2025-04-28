package com.wcs.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;
    private String courseName;
    private String description;
    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> student;
    @ManyToOne
    @JoinColumn(name = "faculty_Id")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "department_id"  )
    private Department department;

}
