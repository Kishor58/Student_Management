package com.wcs.demo.service;

import com.wcs.demo.dto.Course;
import com.wcs.demo.dto.Faculty;
import com.wcs.demo.dto.Student;
import com.wcs.demo.repository.CourseRepository;
import com.wcs.demo.repository.FacultyRepository;
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

    @Autowired
    private FacultyRepository facultyRepository;

    public Course addCourse(Course c){
        return courseRepository.save(c);
    }

    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public Optional<Course> findCourseById(Long id){
        return courseRepository.findById(id);
    }

    public String addStudent(Long studentId, Long courseId){
        Student student=studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student Id Not found"));
        Course course=courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Course ID not found"));

        if(student.getCourses().contains(course)){
            return "Student has already enrolled in this course ";
        }
        course.getStudent().add(student);
        student.getCourses().add(course);

        studentRepository.save(student);
        courseRepository.save(course);
        return course.getCourseName()+":"+" Course to Enrolled to "+student.getName();
    }
    public Course assignFacultyToCourse(Long courseId, Long facultyId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        course.setFaculty(faculty);
        return courseRepository.save(course);
    }

}
