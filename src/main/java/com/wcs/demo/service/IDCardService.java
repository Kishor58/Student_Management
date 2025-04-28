package com.wcs.demo.service;

import com.wcs.demo.dto.IDCard;
import com.wcs.demo.dto.Student;
import com.wcs.demo.repository.IDCardRepository;
import com.wcs.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IDCardService {

    @Autowired
    private final IDCardRepository idCardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public IDCardService(IDCardRepository idCardRepository) {
        this.idCardRepository = idCardRepository;
    }

    public IDCard saveIDCard(IDCard idCard) {
        return idCardRepository.save(idCard);
    }

    public IDCard getIDCardById(Long id) {
        return idCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID Card not found with id " + id));
    }

    public List<IDCard> getAllIDCards() {
        return idCardRepository.findAll();
    }

    public IDCard updateIDCard(Long id, IDCard idCard) {
        IDCard existing = getIDCardById(id);
        existing.setCardNumber(idCard.getCardNumber());
        existing.setIssueDate(idCard.getIssueDate());
        existing.setExpiryDate(idCard.getExpiryDate());
        return idCardRepository.save(existing);
    }

    public void deleteIDCard(Long id) {
        idCardRepository.deleteById(id);
    }
    public IDCard assignIDCardToStudent(Long studentId, IDCard idCard) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        LocalDate today = LocalDate.now();
        LocalDate expiry = today.plusYears(4);

        idCard.setIssueDate(today);
        idCard.setExpiryDate(expiry);
        idCard.setStudent(student);

        return idCardRepository.save(idCard);
    }


}

