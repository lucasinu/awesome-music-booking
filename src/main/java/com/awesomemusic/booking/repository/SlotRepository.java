package com.awesomemusic.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awesomemusic.booking.entity.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long> {
	
	Optional<Slot> findByName(String slotName);
		
}

