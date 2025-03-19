package com.awesomemusic.booking.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.awesomemusic.booking.dto.RoomDto;
import com.awesomemusic.booking.entity.Booking;
import com.awesomemusic.booking.entity.Room;
import com.awesomemusic.booking.entity.Slot;
import com.awesomemusic.booking.mapper.RoomMapper;
import com.awesomemusic.booking.repository.BookingRepository;
import com.awesomemusic.booking.repository.RoomRepository;
import com.awesomemusic.booking.repository.SlotRepository;
import com.awesomemusic.booking.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {
	
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;

    public RoomServiceImpl(RoomRepository roomRepository, BookingRepository bookingRepository, SlotRepository slotRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.slotRepository = slotRepository;
    }
    
    public Optional<RoomDto> createRoom(RoomDto roomDto) {
        Room room = RoomMapper.mapToRoom(roomDto);
        Room savedRoom = roomRepository.save(room);
        return Optional.of(RoomMapper.mapToRoomDto(savedRoom));
    }

    public Optional<List<RoomDto>> getAvailableRooms(LocalDate date, String slotName) {
    	
        Optional<Slot> optionalSlot = slotRepository.findByName(slotName);
        
        if(optionalSlot.isEmpty()) {
        	return Optional.empty();
        }
        
        Slot slot = optionalSlot.get();
    	
        // Finds all booked room by date and slot
        List<Booking> bookings = bookingRepository.findByBookingDateAndSlot(date, slot);
        List<Room> bookedRooms = bookings.stream().map(Booking::getRoom).toList();
        
        // Returns available rooms
        return Optional.of(roomRepository.findAll().stream()
                .filter( room -> !bookedRooms.contains(room))
        		.map( (room) -> RoomMapper.mapToRoomDto(room) )
                .toList());
    }

	@Override
	public List<RoomDto> getAllRooms() {
		return roomRepository.findAll().stream()
    			.map( (room) -> RoomMapper.mapToRoomDto(room) )
    			.collect(Collectors.toList());
	}


}