package com.wcs.demo.service;

import com.wcs.demo.dto.Student;
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
    private StudentRepository repository;


    public Student save(Student s){
        return repository.save(s);
    }

    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    public Optional<Student> findById(long id){
        return repository.findById(id);
    }

    public Optional<Student> updatestudent(long id,Student newData){
        return repository.findById(id)
                .map(student->{
                    student.setName(newData.getName());
                    student.setEmail(newData.getEmail());
                    student.setContact(newData.getContact());
                    return repository.save(student);
                });
    }

    public boolean deleteById(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Student> search(String keyword) {
        List<Student> allStudents = repository.findAll();

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
        List<Student> student=repository.searchByKeyword(keyword);
        return student;
    }

    public Page<Student> searchByKeyword(String keyword, Pageable pageable){
        return repository.searchByKeyword(keyword,pageable);
    }



}
