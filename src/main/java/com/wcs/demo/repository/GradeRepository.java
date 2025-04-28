package com.wcs.demo.repository;

import com.wcs.demo.dto.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade,Long> {
}
