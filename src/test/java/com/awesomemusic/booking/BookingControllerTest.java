package com.awesomemusic.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;
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
        booking.setBookingCode("CU0004SL032025XXXXXXXX");

        when(bookingService.createBooking(any(BookingRequest.class)))
                .thenReturn(Optional.of(booking));

        mockMvc.perform(post("/api/bookings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("CU0004SL032025XXXXXXXX"));
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

    @Test
    public void testGetAllBookingsWithOffset() throws Exception {

        Booking booking1 = new Booking();
        booking1.setBookingCode("CU0004SL032025XXXXXXXX");
        booking1.setStatus(BookingStatus.PENDING);

        Booking booking2 = new Booking();
        booking2.setBookingCode("CU0005SL032025YYYYYYYY");
        booking2.setStatus(BookingStatus.ACCEPTED);

        @SuppressWarnings("unchecked")
        // Page is an interface and Spring complains that couldn't find a concrete implementation --> PageImpl which is a concrete implementation of Page
        // Type safety: The expression of type PageImpl needs unchecked conversion to conform to PageImpl<Booking>
        PageImpl<Booking> bookingsPage = Mockito.mock(PageImpl.class);
        when(bookingsPage.getNumber()).thenReturn(0);
        when(bookingsPage.getSize()).thenReturn(2);
        when(bookingsPage.getTotalPages()).thenReturn(1);
        when(bookingsPage.getContent()).thenReturn(List.of(booking1, booking2));
        when(bookingsPage.getTotalElements()).thenReturn(2L);

        when(bookingService.getAllBookings(Mockito.any()))
                .thenReturn(bookingsPage);

        mockMvc.perform(get("/api/bookings")
                .param("offset", "0")
                .param("pageSize", "10")
                .param("sortBy", "bookingDate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].bookingCode").value("CU0004SL032025XXXXXXXX"))
                .andExpect(jsonPath("$.content[1].bookingCode").value("CU0005SL032025YYYYYYYY"))
                .andExpect(jsonPath("$.totalElements").value(2));
    }
	
}
