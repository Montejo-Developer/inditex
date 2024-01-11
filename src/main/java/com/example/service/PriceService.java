package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.PriceRequestDTO;
import com.example.dto.PriceResponseDTO;
import com.example.model.PriceEntity;
import com.example.repository.PriceRepository;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;
    
    public PriceResponseDTO getPriceByDateAndProductAndBrand(PriceRequestDTO requestDTO) {
        List<PriceEntity> prices = priceRepository.findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
                requestDTO.getDate(), requestDTO.getProductId(), requestDTO.getBrandId());

        if (!prices.isEmpty()) {
            return mapEntityToResponseDTO(prices.get(0));
        } else {
            throw new Exception("Producto no disponible"); 
        }
    }

    //Se ha valorado usar MapStruct para realizar el mapeo, pero al tener que manejar una unica entidad, se ha optado por realizarlo manualmente.
    private PriceResponseDTO mapEntityToResponseDTO(PriceEntity priceEntity) {
        PriceResponseDTO responseDTO = new PriceResponseDTO();
        responseDTO.setProductId(priceEntity.getProductId());
        responseDTO.setBrandId(priceEntity.getBrandId());
        responseDTO.setPriceList(priceEntity.getPriceList());
        responseDTO.setStartDate(priceEntity.getStartDate());
        responseDTO.setEndDate(priceEntity.getEndDate());
        responseDTO.setFinalPrice(priceEntity.getPrice());
        return responseDTO;
    }
}