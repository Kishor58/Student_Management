package com.wcs.demo.controller;

import com.wcs.demo.Exception.CollegeNotFoundException;
import com.wcs.demo.dto.College;
import com.wcs.demo.dto.Student;
import com.wcs.demo.dto.University;
import com.wcs.demo.service.CollegeService;
import com.wcs.demo.service.StudentService;
import com.wcs.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UniversityService universityService;

    @PostMapping("/save")
    public College register(@RequestBody College college) {
        return collegeService.saveCollege(college);
    }

    @GetMapping("getAll")
    public List<College> getAll() {
        return collegeService.getAllColleges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<College> getCollegeById(@PathVariable Long id) {
        College college = collegeService.getCollegeById(id);
        return ResponseEntity.ok(college);
    }



    @PutMapping("/{id}")
    public College update(@PathVariable Long id, @RequestBody College college) {
        return collegeService.updateCollege(id, college);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        collegeService.deleteCollege(id);
    }

    @GetMapping("/search")
    public List<College> search(@RequestParam String name) {
        return collegeService.searchByName(name);
    }

    @GetMapping("/university/{universityId}")
    public List<College> getByUniversityId(@PathVariable Long universityId) {
        return collegeService.getCollegesByUniversityId(universityId);
    }

    @PutMapping("/{collegeId}/transfer/{universityId}")
    public College transferCollege(
            @PathVariable Long collegeId,
            @PathVariable Long universityId) {
        return collegeService.transferCollege(collegeId, universityId);
    }
    @GetMapping("/{collegeId}/students")
    public List<Student> getStudentsByCollege(@PathVariable Long collegeId) {
        return studentService.getStudentsByCollege(collegeId);
    }
}

