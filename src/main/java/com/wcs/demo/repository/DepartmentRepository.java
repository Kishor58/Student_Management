package com.wcs.demo.repository;

import com.wcs.demo.dto.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
