package com.awesomemusic.booking.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.awesomemusic.booking.dto.RoomDto;
import com.awesomemusic.booking.exception.ErrorResponse;
import com.awesomemusic.booking.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }
    
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableRooms(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate bookingDate,
            @RequestParam String slotName) {

    	Optional<List<RoomDto>> response = roomService.getAvailableRooms(bookingDate, slotName);
    	
    	if(response.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Slot "+slotName+" not found!"));
    	} else {
    		return ResponseEntity.ok(response.get());
    	}

    }

    @PostMapping
    public ResponseEntity<Optional<RoomDto>> createRoom(@RequestBody RoomDto roomDto) {
    	
    	try {
            return ResponseEntity.ok(roomService.createRoom(roomDto));
		} catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in saving: " + e.getMessage());
		}
    }
}