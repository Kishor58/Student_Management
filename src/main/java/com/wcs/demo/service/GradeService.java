package com.wcs.demo.service;

import com.wcs.demo.dto.Course;
import com.wcs.demo.dto.Grade;
import com.wcs.demo.dto.Student;
import com.wcs.demo.repository.CourseRepository;
import com.wcs.demo.repository.GradeRepository;
import com.wcs.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GradeService {
    @Autowired
    private final GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found with id " + id));
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade updateGrade(Long id, Grade grade) {
        Grade existing = getGradeById(id);
        existing.setGradeValue(grade.getGradeValue());
        existing.setRemarks(grade.getRemarks());
        return gradeRepository.save(existing);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }

    public Grade assignGradeToStudent(Long studentId, Long courseId, Grade grade) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

}

