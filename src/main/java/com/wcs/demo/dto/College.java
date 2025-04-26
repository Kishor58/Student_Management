package com.wcs.demo.dto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collegeId;
    private String collegeName;
    private String location;

    @ManyToOne
    @JoinColumn(name = "University_Id")
    private University university;

//    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
//    private List<Department> departments;
//
//    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
//    private List<Student> students;

}
