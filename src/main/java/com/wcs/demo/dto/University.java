package com.wcs.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long universityId;
    private String universityName;
    private String location ;
    @OneToMany(mappedBy = "university" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<College> collegeList;
}
