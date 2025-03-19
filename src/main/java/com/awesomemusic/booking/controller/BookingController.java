package com.awesomemusic.booking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awesomemusic.booking.dto.BookingRequest;
import com.awesomemusic.booking.entity.Booking;
import com.awesomemusic.booking.exception.ErrorResponse;
import com.awesomemusic.booking.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {        
        Optional<Booking> booking = bookingService.createBooking(request);
        
        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get().getBookingCode());
        } else {
            return ResponseEntity.badRequest().body(new ErrorResponse("Selected Room is already booked for this combination of date and slot."));
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
    
    @GetMapping("/{bookingCode}")
    public ResponseEntity<?> getBookingByBookingCode(@PathVariable String bookingCode) {
        Optional<Booking> booking = bookingService.getBookingByBookingCode(bookingCode);
    	
    	if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.badRequest().body(new ErrorResponse("No Booking with the insert code"));
        }    	
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Booking> updateBookingStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, status));
    }
    
    @PatchMapping("/{id}/accept")
    public ResponseEntity<Booking> acceptBooking(@PathVariable Long id) {
    	return ResponseEntity.ok(bookingService.acceptBooking(id));
    }
}