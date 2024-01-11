package com.example.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.PriceEntity;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
	
	List<PriceEntity> findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
            LocalDateTime checkDate, Long productId, Long brandId);
}