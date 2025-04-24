package com.wcs.demo.service;

import com.wcs.demo.dto.Course;
import com.wcs.demo.dto.Student;
import com.wcs.demo.repository.CourseRepository;
import com.wcs.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Course addCourse(Course c){
        return courseRepository.save(c);
    }

    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public Optional<Course> findCourseById(Long id){
        return courseRepository.findById(id);
    }

    public Course addStudent(Long studentId, Long courseId){
        Student student=studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student Id Not found"));
        Course course=courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Course ID not found"));

        course.getStudent().add(student);
        student.getCourses().add(course);

        studentRepository.save(student);
        return courseRepository.save(course);
    }
}
