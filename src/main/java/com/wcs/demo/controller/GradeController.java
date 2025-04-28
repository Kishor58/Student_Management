package com.wcs.demo.controller;

import com.wcs.demo.dto.Grade;
import com.wcs.demo.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> assignGradeToStudent(@PathVariable Long studentId, @PathVariable Long courseId, @RequestBody Grade grade) {
        Grade savedGrade = gradeService.assignGradeToStudent(studentId, courseId, grade);
        return ResponseEntity.ok(savedGrade);
    }

    @GetMapping("/getGrade/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        Grade grade = gradeService.getGradeById(id);
        return ResponseEntity.ok(grade);
    }

    @GetMapping("/getAllGrades")
    public ResponseEntity<List<Grade>> getAllGrades() {
        List<Grade> grades = gradeService.getAllGrades();
        return ResponseEntity.ok(grades);
    }

    @PutMapping("/updateGrade/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        Grade updatedGrade = gradeService.updateGrade(id, grade);
        return ResponseEntity.ok(updatedGrade);
    }

    @DeleteMapping("/deleteGrade/{id}")
    public ResponseEntity<String> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.ok("Grade deleted successfully.");
    }
}

