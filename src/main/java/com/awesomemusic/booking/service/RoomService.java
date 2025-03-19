package com.awesomemusic.booking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.awesomemusic.booking.dto.RoomDto;

public interface RoomService {

    List<RoomDto> getAllRooms();
    Optional<List<RoomDto>> getAvailableRooms(LocalDate bookingDate, String slotName);
    Optional<RoomDto> createRoom(RoomDto roomDto);
	
}
