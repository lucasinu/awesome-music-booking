package com.awesomemusic.booking.mapper;

import com.awesomemusic.booking.dto.SlotDto;
import com.awesomemusic.booking.entity.Slot;

public class SlotMapper {
	
    public static SlotDto mapToSlotDto(Slot slot) {
        return new SlotDto(
            slot.getIdSlot(),
        	slot.getName(),
        	"From "+slot.getStartHour()+" to "+slot.getEndHour()
        );
    }

    public static Slot mapToSlot(SlotDto slotDto) {
        return new Slot(
            slotDto.getIdSlot(),
            slotDto.getName(),
            slotDto.getPeriod().split(" ")[1],
            slotDto.getPeriod().split(" ")[3]
        );
    }
}
