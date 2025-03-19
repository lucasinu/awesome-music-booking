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

import com.awesomemusic.booking.entity.Slot;
import com.awesomemusic.booking.repository.SlotRepository;

@RestController
@RequestMapping("/api/slots")
public class SlotController {
    private final SlotRepository slotRepository;

    public SlotController(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @GetMapping
    public ResponseEntity<List<Slot>> getAllSlots() {
        return ResponseEntity.ok(slotRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Slot> createSlot(@RequestBody Slot slot) {
    	try {
    		return ResponseEntity.ok(slotRepository.save(slot));
    	} catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in saving: " + e.getMessage());
		}
    }

    @GetMapping("/{name}")
    public ResponseEntity<Optional<Slot>> getSlotByName(@PathVariable String name) {
        return ResponseEntity.ok(slotRepository.findByName(name));
    }
}