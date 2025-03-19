package com.awesomemusic.booking.service;

import java.util.List;
import java.util.Optional;

import com.awesomemusic.booking.dto.SlotDto;

public interface SlotService {

	List<SlotDto> getAllSlots();
	Optional<SlotDto> createSlot(SlotDto slotDto);
	Optional<SlotDto> getByName(String name);

}
