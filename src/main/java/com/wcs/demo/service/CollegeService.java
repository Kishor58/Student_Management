package com.wcs.demo.service;

import com.wcs.demo.dto.College;
import com.wcs.demo.dto.University;
import com.wcs.demo.repository.CollegeRepository;
import com.wcs.demo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public College saveCollege(College college) {
        return collegeRepository.save(college);
    }

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public College getCollegeById(Long id) {
        return collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("College not found"));
    }

    public College updateCollege(Long id, College updated) {
        College existing = getCollegeById(id);
        existing.setCollegeName(updated.getCollegeName());
        existing.setLocation(updated.getLocation());
        existing.setUniversity(updated.getUniversity());
        return collegeRepository.save(existing);
    }

    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }

    public List<College> searchByName(String name) {
        return collegeRepository.findByCollegeNameContainingIgnoreCase(name);
    }

    public List<College> getCollegesByUniversityId(Long universityId) {
        return collegeRepository.findByUniversity_UniversityId(universityId);
    }

    public College transferCollege(Long collegeId, Long universityId) {
        College college = getCollegeById(collegeId);
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new RuntimeException("University not found"));
        college.setUniversity(university);
        return collegeRepository.save(college);
    }
}
