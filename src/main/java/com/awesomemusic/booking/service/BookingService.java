package com.awesomemusic.booking.service;

import java.util.List;
import java.util.Optional;

import com.awesomemusic.booking.dto.BookingRequest;
import com.awesomemusic.booking.entity.Booking;

public interface BookingService {
	
    Optional<Booking> createBooking(BookingRequest request);
    List<Booking> getAllBookings();
    Booking updateBookingStatus(Long id, String status);
    Booking acceptBooking(Long id);
    Optional<Booking> getBookingByBookingCode(String bookingCode);
    
}
