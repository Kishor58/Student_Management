package com.wcs.demo.repository;

import com.wcs.demo.dto.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University,Long> {

    List<University> findByUniversityNameContainingIgnoreCase(String name);

    @Query("SELECT u FROM University u WHERE " +
            "LOWER(u.universityName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.location) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<University> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
