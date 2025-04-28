package com.wcs.demo.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "faculty")
    private List<Course> courses;
}

