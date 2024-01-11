package com.example.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.controller.PriceController;
import com.example.model.PriceEntity;
import com.example.service.PriceService;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Test
    public void testPriceEndpointAt1000OnDay14() throws Exception {
        performAndVerifyPriceRequest("2020-06-14T10:00:00", 35455L, 1L, 1);
    }

    @Test
    public void testPriceEndpointAt1600OnDay14() throws Exception {
        performAndVerifyPriceRequest("2020-06-14T16:00:00", 35455L, 1L, 4);
    }

    @Test
    public void testPriceEndpointAt2100OnDay14() throws Exception {
        performAndVerifyPriceRequest("2020-06-14T21:00:00", 35455L, 1L, 4);
    }

    @Test
    public void testPriceEndpointAt1000OnDay15() throws Exception {
        performAndVerifyPriceRequest("2020-06-15T10:00:00", 35455L, 1L, 3);
    }

    @Test
    public void testPriceEndpointAt2100OnDay16() throws Exception {
        performAndVerifyPriceRequest("2020-06-16T21:00:00", 35455L, 1L, null);
    }

    private void performAndVerifyPriceRequest(String date, Long productId, Long brandId, Integer expectedPriceList) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("date", date)
                .param("productId", productId.toString())
                .param("brandId", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(productId))
            .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(brandId))
            .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(expectedPriceList))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").isNotEmpty())
            .andReturn();

        String content = result.getResponse().getContentAsString();
        if (expectedPriceList != null) {
            assertEquals(false, content.isEmpty());
        } else {
            assertEquals(true, content.isEmpty());
        }
    }
}