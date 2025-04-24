package com.wcs.demo.controller;

import com.wcs.demo.dto.Course;
import com.wcs.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping("/addCourse")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        Course added= service.addCourse(course);
        return ResponseEntity.ok(added);
    }
    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses(){
        return service.getAllCourse();
    }

    @GetMapping("/findCourseById/{id}")
    public ResponseEntity<?> findCourseById(@PathVariable Long id){
        Optional<Course> course=service.findCourseById(id);
        if(!course.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This ID is not found ,Try for another ID");
        }
        return ResponseEntity.ok(course.get());
    }

    @GetMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Course> addStudent(@PathVariable Long studentId,@PathVariable Long courseId){
        Course updateCourse= service.addStudent(studentId,courseId);
        return ResponseEntity.ok(updateCourse);
    }

}
