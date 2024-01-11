package com.example.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PriceRequestDTO;
import com.example.dto.PriceResponseDTO;
import com.example.model.PriceEntity;
import com.example.service.PriceService;

@RestController
@RequestMapping("/prices")
public class PriceController {
    @Autowired
    private PriceService priceService;
    
    @GetMapping
    public ResponseEntity<PriceResponseDTO> getPrice(
    		@RequestParam PriceRequestDTO requestDTO){
    	
        return new ResponseEntity<>(priceService.getPriceByDateAndProductAndBrand(requestDTO), HttpStatus.OK);
    }
}