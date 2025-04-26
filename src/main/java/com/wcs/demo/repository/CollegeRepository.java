package com.wcs.demo.repository;

import com.wcs.demo.dto.College;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegeRepository extends JpaRepository<College,Long> {

    List<College> findByUniversity_UniversityId(Long universityId);

    List<College> findByCollegeNameContainingIgnoreCase(String name);
}
