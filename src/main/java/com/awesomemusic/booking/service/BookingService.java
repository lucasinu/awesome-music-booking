package com.awesomemusic.booking.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.awesomemusic.booking.dto.BookingRequest;
import com.awesomemusic.booking.entity.Booking;

public interface BookingService {
	
    Optional<Booking> createBooking(BookingRequest request);
    Page<Booking> getAllBookings(PageRequest pageRequest);
    Booking updateBookingStatus(Long id, String status);
    Booking acceptBooking(Long id);
    Optional<Booking> getBookingByBookingCode(String bookingCode);
    
}
