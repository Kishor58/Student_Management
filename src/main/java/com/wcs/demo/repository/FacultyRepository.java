package com.wcs.demo.repository;

import com.wcs.demo.dto.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
}
