package com.awesomemusic.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Optional;

import com.awesomemusic.booking.dto.BookingRequest;
import com.awesomemusic.booking.entity.Booking;
import com.awesomemusic.booking.entity.BookingStatus;
import com.awesomemusic.booking.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class BookingControllerTest {

    @MockitoBean
	private BookingService bookingService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @Test
    public void testCreateBooking() throws Exception {

        BookingRequest request = new BookingRequest();
        
        // Set needed fields
        Booking booking = new Booking();
        booking.setBookingCode("CU0004SL032025");

        when(bookingService.createBooking(any(BookingRequest.class)))
                .thenReturn(Optional.of(booking));

        mockMvc.perform(post("/api/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("CU0004SL032025"));
    }
    
    @Test
    public void testUpdateBookingStatus() throws Exception {

    	Booking booking = new Booking();
        booking.setStatus(BookingStatus.ACCEPTED);

        Mockito.when(bookingService.updateBookingStatus(Mockito.anyLong(), Mockito.anyString()))
               .thenReturn(booking);

        mockMvc.perform(patch("/api/bookings/1/status")
                .param("status", "ACCEPTED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACCEPTED"));
    }
	
}
