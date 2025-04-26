package com.wcs.demo.controller;

import com.wcs.demo.dto.College;
import com.wcs.demo.dto.University;
import com.wcs.demo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @PostMapping("/save")
    public University register(@RequestBody University university){
        return universityService.saveUniversity(university);
    }

    @GetMapping("/getAllUniversity")
    public List<University> getAllUniversity(){
        return universityService.getAllUniversity();
    }
    @GetMapping("/universityById/{universityId}")
    public University getUniversityById(@PathVariable Long universityId){
        return universityService.getUniversityById(universityId);
    }
    @PutMapping("/update/{universityId}")
    public String updateUniversity(@PathVariable Long universityId, @RequestBody University university) {
        return universityService.updateUniversity(universityId, university);
    }

    @DeleteMapping("/delete")
    public String deleteUniversity(@RequestBody University university) {
        return universityService.deleteUniversity(university);
    }
    @GetMapping("/searchOnPage")
    public ResponseEntity<Object> searchByKeyword(@RequestParam String keyword, @RequestParam int page, @RequestParam int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<University> resultPage = universityService.searchByKeyword(keyword, pageable);

        if (page >= resultPage.getTotalPages()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("This Page is not available. Please enter a lower page number.");
        }
        return ResponseEntity.ok(resultPage);
    }

    @GetMapping("/{universityId}/colleges")
    public List<College> getAllColleges(@PathVariable Long universityId) {
        return universityService.getAllTheColleges(universityId);
    }
}
