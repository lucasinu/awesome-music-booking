package com.awesomemusic.booking.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.awesomemusic.booking.dto.BookingRequest;
import com.awesomemusic.booking.entity.Booking;
import com.awesomemusic.booking.entity.BookingStatus;
import com.awesomemusic.booking.entity.Room;
import com.awesomemusic.booking.entity.Slot;
import com.awesomemusic.booking.repository.BookingRepository;
import com.awesomemusic.booking.repository.RoomRepository;
import com.awesomemusic.booking.repository.SlotRepository;
import com.awesomemusic.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	
    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;
    private final RoomRepository roomRepository;
    
    public BookingServiceImpl(BookingRepository bookingRepository, SlotRepository slotRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.slotRepository = slotRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * Creates a booking if the selected room is available for the given date and slot.
     *
     * @param request The booking request details.
     * @return Optional containing the booking if successful, empty if the room is unavailable.
     */
	@Override
	public Optional<Booking> createBooking(BookingRequest request) {
		
		//First check the date
	    if (request.getBookingDate().isBefore(LocalDate.now())) {
	        throw new IllegalArgumentException("The booking date must be today or a future date.");
	    }
		
        Room room = roomRepository.findById(request.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
		Slot slot = slotRepository.findById(request.getSlotId()).orElseThrow(() -> new RuntimeException("Slot not found"));
				
        // Check if the room is available for the selected Slot in the selected date
        boolean isAvailable = isRoomAvailable(room, request.getBookingDate(), slot);
        if (!isAvailable) {
            return Optional.empty();
        }		
        
        Booking booking = new Booking();
        booking.setCustomerName(request.getCustomerName());
        booking.setSlot(slot);
        booking.setRoom(room);
        booking.setBookingDate(request.getBookingDate());
        booking.setInputDate(LocalDateTime.now());
        booking.setStatus(BookingStatus.PENDING);
        booking.setBookingCode(generateBookingCode(booking));
        
        return Optional.of(bookingRepository.save(booking));
	}

	/**
	 * Generates a unique booking code based on customer name, room ID, slot name, and booking date.
	 * 
	 * The generated code follows this format:  
	 * [First two letters of customer name][Room ID (4 digits)][First two letters of slot name][Booking date (ddMMYY)]  
	 * 
	 * Example:  
	 * - Customer: "Alice"  
	 * - Room ID: 12  
	 * - Slot Name: "Morning"  
	 * - Booking Date: 19th March 2025  
	 * - Generated Code: **AL0012MO190325**
	 * 
	 * @param booking The booking entity containing the details used to generate the code.
	 * @return A unique booking code as a string.
	 */
    private String generateBookingCode(Booking booking) {
        
    	// Using StringBuilder for better performance and memory efficiency compared to String concatenation
    	StringBuilder code = new StringBuilder();
    	
    	code.append( (booking.getCustomerName().length() >= 2) 
    			? booking.getCustomerName().substring(0, 2).toUpperCase()
				: (booking.getCustomerName() + "XX").substring(0, 2).toUpperCase() );
    	
    	code.append(String.format("%04d", booking.getRoom().getIdRoom()));
    	
    	code.append( (booking.getSlot().getName().length() >= 2) 
    			? booking.getSlot().getName().substring(0, 2).toUpperCase()
				: (booking.getSlot().getName() + "XX").substring(0, 2).toUpperCase() );
    	 
    	code.append(booking.getBookingDate().format(DateTimeFormatter.ofPattern("ddMMYY")));
    	
        // Ensuring uniqueness within the system context
    	return code.toString();
    
    }
    
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    @Override
    public Booking updateBookingStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

            try {
                booking.setStatus(BookingStatus.valueOf(status.toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Status not valid");
            }

            return bookingRepository.save(booking);

    }
	
    public boolean isRoomAvailable(Room room, LocalDate bookingDate, Slot slot) {
    	return !bookingRepository.existsByRoomAndBookingDateAndSlotAndStatusNot(room, bookingDate, slot, BookingStatus.REFUSED);
    }

	@Override
	public Booking acceptBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));		
        booking.setStatus(BookingStatus.ACCEPTED);
        
        booking.setAcceptanceDate(LocalDateTime.now());
        return bookingRepository.save(booking);
        
	}

	@Override
	public Optional<Booking> getBookingByBookingCode(String bookingCode) {
		return bookingRepository.findByBookingCode(bookingCode);
	}
    
}
