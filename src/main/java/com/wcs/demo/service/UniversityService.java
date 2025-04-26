package com.wcs.demo.service;

import com.wcs.demo.dto.College;
import com.wcs.demo.dto.University;
import com.wcs.demo.repository.CollegeRepository;
import com.wcs.demo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    public University saveUniversity(University university){
        return universityRepository.save(university);
    }
    public List<University> getAllUniversity(){
        return universityRepository.findAll();
    }
    public University getUniversityById(Long universityId){
        return universityRepository.findById(universityId)
                .orElseThrow(()->new RuntimeException("University not found"));
    }
    public String updateUniversity(Long universityId ,University university){
        Optional<University> optionalUniversity=universityRepository.findById(universityId);

        if(optionalUniversity.isPresent()){
            University exiteUniversity=optionalUniversity.get();
            exiteUniversity.setUniversityName(university.getUniversityName());
            exiteUniversity.setLocation(university.getLocation());
            universityRepository.save(exiteUniversity);
            return "University updated successfully.";
        }else{
            return "University not found with this id"+universityId;
        }
    }
    public String  deleteUniversity(University university){
        universityRepository.delete(university);
        return "University is deleted successfully";
    }

    public Page<University> searchByKeyword(String keyword, Pageable pageable){
        return universityRepository.searchByKeyword(keyword,pageable);
    }
    public List<College> getAllTheColleges(Long universityId){
        return collegeRepository.findByUniversity_UniversityId(universityId);
    }
}
