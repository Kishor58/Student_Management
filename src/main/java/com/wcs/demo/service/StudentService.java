package com.wcs.demo.service;

import com.wcs.demo.dto.Course;
import com.wcs.demo.dto.Student;
import com.wcs.demo.repository.CourseRepository;
import com.wcs.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;


    public Student save(Student s){
        return studentRepository.save(s);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> findById(long id){
        return studentRepository.findById(id);
    }

    public Optional<Student> updatestudent(long id,Student newData){
        return studentRepository.findById(id)
                .map(student->{
                    student.setName(newData.getName());
                    student.setEmail(newData.getEmail());
                    student.setContact(newData.getContact());
                    return studentRepository.save(student);
                });
    }

    public boolean deleteById(Long id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Student> search(String keyword) {
        List<Student> allStudents = studentRepository.findAll();

        return allStudents.stream()
                .filter(student ->
                        (keyword != null && (
                                student.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                                        student.getEmail().toLowerCase().contains(keyword.toLowerCase()) ||
                                        String.valueOf(student.getContact()).contains(keyword)
                        ))
                ).collect(Collectors.toList());
    }
    public List<Student> searchBySql(String keyword){
        List<Student> student=studentRepository.searchByKeyword(keyword);
        return student;
    }

    public Page<Student> searchByKeyword(String keyword, Pageable pageable){
        return studentRepository.searchByKeyword(keyword,pageable);
    }

    public Student addCourse(Long studentId,Long courseId){
        Student student=studentRepository.findById(studentId).orElseThrow(()-> new RuntimeException("Student Id not found "));
        Course course=courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Course Id not found"));

        student.getCourses().add(course);
        course.getStudent().add(student);

        courseRepository.save(course);
        return studentRepository.save(student);
    }




}
