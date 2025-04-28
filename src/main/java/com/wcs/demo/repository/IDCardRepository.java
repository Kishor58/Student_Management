package com.wcs.demo.repository;

import com.wcs.demo.dto.IDCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDCardRepository extends JpaRepository<IDCard,Long> {
}
