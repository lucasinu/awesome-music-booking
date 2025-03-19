package com.awesomemusic.booking.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.awesomemusic.booking.dto.SlotDto;
import com.awesomemusic.booking.entity.Slot;
import com.awesomemusic.booking.mapper.SlotMapper;
import com.awesomemusic.booking.repository.SlotRepository;
import com.awesomemusic.booking.service.SlotService;

@Service
public class SlotServiceImpl implements SlotService {
	
    private final SlotRepository slotRepository;

	public SlotServiceImpl(SlotRepository slotRepository) {
		this.slotRepository = slotRepository;
	}

	@Override
	public List<SlotDto> getAllSlots() {
		return slotRepository.findAll().stream()
    			.map( (slot) -> SlotMapper.mapToSlotDto(slot) )
    			.collect(Collectors.toList());
	}

	@Override
	public Optional<SlotDto> createSlot(SlotDto slotDto) {
        Slot slot = SlotMapper.mapToSlot(slotDto);
        Slot savedSlot = slotRepository.save(slot);
        return Optional.of(SlotMapper.mapToSlotDto(savedSlot));
	}
	
	@Override
	public Optional<SlotDto> getByName(String name) {
		Optional<Slot> slot = slotRepository.findByName(name);
		return slot.isEmpty() ? Optional.empty() : Optional.of(SlotMapper.mapToSlotDto(slot.get()));
	}

}
