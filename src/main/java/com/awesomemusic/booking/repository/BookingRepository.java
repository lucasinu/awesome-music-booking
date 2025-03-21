package com.awesomemusic.booking.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awesomemusic.booking.entity.Booking;
import com.awesomemusic.booking.entity.BookingStatus;
import com.awesomemusic.booking.entity.Room;
import com.awesomemusic.booking.entity.Slot;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	boolean existsByRoomAndBookingDateAndSlot(Room room, LocalDate bookingDate, Slot slot);
	
    List<Booking> findByBookingDateAndSlot(LocalDate date, Slot slot);

	Optional<Booking> findByBookingCode(String bookingCode);

	boolean existsByRoomAndBookingDateAndSlotAndStatusNot(Room room, LocalDate bookingDate, Slot slot,
			BookingStatus refused);
	
}
