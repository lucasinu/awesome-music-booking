package com.awesomemusic.booking.mapper;

import com.awesomemusic.booking.dto.RoomDto;
import com.awesomemusic.booking.entity.Room;

public class RoomMapper {
	
    public static RoomDto mapToRoomDto(Room room) {
        return new RoomDto(
            room.getIdRoom(),
        	room.getName(),
        	room.getDescription()
        );
    }

    public static Room mapToRoom(RoomDto roomDto) {
        return new Room(
            roomDto.getIdRoom(),
            roomDto.getName(),
            roomDto.getDescription()
        );
    }
}
