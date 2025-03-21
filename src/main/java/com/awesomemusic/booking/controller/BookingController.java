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
import com.awesomemusic.booking.entity.BookingStatus;
import com.awesomemusic.booking.exception.ErrorResponse;
import com.awesomemusic.booking.service.BookingService;

/**
 * Controller for managing booking-related operations.
 * Provides endpoints for creating, retrieving, and updating bookings.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Creates a new booking.
     * 
     * @param request Booking request containing necessary details.
     * @return ResponseEntity with the booking code if successful,
     *         or an error message if the room is already booked.
     */
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {        
        Optional<Booking> booking = bookingService.createBooking(request);
        
        //If the createBooking() method returns a Booking, it means the Booking is correctly saved and we have to return the bookingCode 
        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get().getBookingCode());
        } else {
            return ResponseEntity.badRequest().body(new ErrorResponse("Selected Room is already booked for this combination of date and slot."));
        }
    }

    /**
     * Retrieves a list of all bookings.
     *
     * @return ResponseEntity containing a list of all bookings stored in the database.
     */
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
    
    /**
     * Retrieves a booking by its bookingCode.
     *
     * @param bookingCode The bookingCode of the booking to search for.
     * @return ResponseEntity containing an Optional of the found Booking, 
     *         or an error message if no booking is found.
     */
    @GetMapping("/{bookingCode}")
    public ResponseEntity<?> getBookingByBookingCode(@PathVariable String bookingCode) {
        Optional<Booking> booking = bookingService.getBookingByBookingCode(bookingCode);
    	
    	if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.badRequest().body(new ErrorResponse("No Booking with the insert code"));
        }    	
    }

    /**
     * Updates the status of a booking based on the given ID and status.
     *
     * @param id The unique identifier of the booking to be updated.
     * @param status The new status to be set (e.g., ACCEPTED, REFUSED, CANCELED).
     * @return ResponseEntity containing the updated booking.
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Booking> updateBookingStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, status));
    }

    /**
     * Accepts a booking by updating its status to ACCEPTED.
     *
     * @param id The unique identifier of the booking to be accepted.
     * @return ResponseEntity containing the updated booking with status set to ACCEPTED.
     */
    @PatchMapping("/{id}/accept")
    public ResponseEntity<Booking> acceptBooking(@PathVariable Long id) {
    	return ResponseEntity.ok(bookingService.acceptBooking(id));
    }
    
    /**
     * Refuses a booking by updating its status to REFUSED.
     *
     * @param id The unique identifier of the booking to be accepted.
     * @return ResponseEntity containing the updated booking with status set to REFUSED.
     */
    @PatchMapping("/{id}/refuse")
    public ResponseEntity<Booking> refuseBooking(@PathVariable Long id) {
    	return ResponseEntity.ok(bookingService.updateBookingStatus(id, BookingStatus.REFUSED.toString()));
    }
}