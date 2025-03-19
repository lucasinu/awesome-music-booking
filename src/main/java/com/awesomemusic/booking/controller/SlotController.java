package com.awesomemusic.booking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.awesomemusic.booking.dto.SlotDto;
import com.awesomemusic.booking.exception.ErrorResponse;
import com.awesomemusic.booking.service.SlotService;


/**
 * Controller for managing slot-related operations.
 * Provides endpoints for creating and retrieving slots.
 */
@RestController
@RequestMapping("/api/slots")
public class SlotController {
    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    /**
     * Retrieves a list of all slots.
     *
     * @return ResponseEntity containing a list of all slots stored in the database.
     */
    @GetMapping
    public ResponseEntity<List<SlotDto>> getAllSlots() {
        return ResponseEntity.ok(slotService.getAllSlots());
    }

    /**
     * Creates a new slot.
     * 
     * @param Slot containing necessary details.
     * @return ResponseEntity with the Slot if successful,
     *         or an error message if not.
     */
    @PostMapping
    public ResponseEntity<Optional<SlotDto>> createSlot(@RequestBody SlotDto slotDto) {
    	try {
    		return ResponseEntity.ok(slotService.createSlot(slotDto));
    	} catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in saving: " + e.getMessage());
		}
    }

    /**
     * Retrieves a slot by its name.
     *
     * @param name The name of the slot to search for.
     * @return ResponseEntity containing an Optional of the found Slot, 
     *         or an empty Optional if no slot is found.
     */
    @GetMapping("/{name}")
    public ResponseEntity<?> getSlotByName(@PathVariable String name) {
    	
    	Optional<SlotDto> response = slotService.getByName(name);
    	
    	if(response.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Slot "+name+" not found!"));
    	} else {
    		return ResponseEntity.ok(response.get());
    	}    	
    }
}