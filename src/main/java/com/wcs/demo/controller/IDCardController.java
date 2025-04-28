package com.wcs.demo.controller;

import com.wcs.demo.dto.IDCard;
import com.wcs.demo.service.IDCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/id_cards")
public class IDCardController {

    private final IDCardService idCardService;

    public IDCardController(IDCardService idCardService) {
        this.idCardService = idCardService;
    }

    @PostMapping("/student/{studentId}")
    public ResponseEntity<IDCard> createIDCard(@PathVariable Long studentId, @RequestBody IDCard idCard) {
        IDCard savedIDCard = idCardService.assignIDCardToStudent(studentId, idCard);
        return ResponseEntity.ok(savedIDCard);
    }

    @GetMapping("/getIdCard/{id}")
    public ResponseEntity<IDCard> getIDCard(@PathVariable Long id) {
        IDCard idCard = idCardService.getIDCardById(id);
        return ResponseEntity.ok(idCard);
    }

    @GetMapping("/getAllIdCards")
    public ResponseEntity<List<IDCard>> getAllIDCards() {
        List<IDCard> idCards = idCardService.getAllIDCards();
        return ResponseEntity.ok(idCards);
    }

    @PutMapping("/updateIdCard/{id}")
    public ResponseEntity<IDCard> updateIDCard(@PathVariable Long id, @RequestBody IDCard idCard) {
        IDCard updatedIDCard = idCardService.updateIDCard(id, idCard);
        return ResponseEntity.ok(updatedIDCard);
    }

    @DeleteMapping("/deleteIdCard/{id}")
    public ResponseEntity<String> deleteIDCard(@PathVariable Long id) {
        idCardService.deleteIDCard(id);
        return ResponseEntity.ok("ID Card deleted successfully.");
    }
}

