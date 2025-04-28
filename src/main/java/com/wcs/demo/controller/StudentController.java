package com.wcs.demo.controller;

import com.wcs.demo.dto.Student;
import com.wcs.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private  StudentService service;

    @PostMapping(value = "/save", consumes = "application/json")
    public Student save(@RequestBody Student student) {
        return service.save(student);
    }

    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent(){
        return service.getAllStudents();
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByID(@PathVariable("id") long id) {
        Optional<Student> student=service.findById(id);
        if(!student.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Sorry with this ID  i don't have any student data !");
        }
        return ResponseEntity.ok(student.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student newData){
        return service.updatestudent(id,newData)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean deleted=service.deleteById(id);
        return deleted? ResponseEntity.ok("User Account Deleted"):ResponseEntity.notFound().build();
    }
    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String keyword){
        return service.search(keyword);
    }

    @GetMapping("/searchBySql")
    public List<Student> searchById(String keyword){
        return service.searchBySql(keyword);
    }

 /*   Here, ResponseEntity<?> means:

    We're not committing to a specific return type.
    The body might be empty, a string, an error message, or even a complex object.*/

    @GetMapping("searchOnPage")
    public ResponseEntity<Object> searchByKeyword(@RequestParam String keyword, @RequestParam int page, @RequestParam int size){
        Pageable pageable= PageRequest.of(page,size);
        Page<Student> resultPage= service.searchByKeyword(keyword,pageable);
        if(page>=resultPage.getTotalPages()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("This Page is not available put same lower page number");
        }
        return ResponseEntity.ok(resultPage);
    }

    @PostMapping("{studentId}/courses/{courseId}")
    public ResponseEntity<String> addCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        String message = service.addCourse(studentId, courseId);
        return ResponseEntity.ok(message);
    }
    @PostMapping("/{studentId}/college/{collegeId}")
    public ResponseEntity<String> assignCollege(@PathVariable Long studentId, @PathVariable Long collegeId) {
        String result = service.assignCollege(studentId, collegeId);
        return ResponseEntity.ok(result);
    }


}
