package com.awesomemusic.booking.dto;

/**
 * Data Transfer Object for Slot entity.
 */
public class SlotDto {
	
	private Long idSlot;
	private String name;
	private String period;
	
	public SlotDto() {
		super();
	}

	public SlotDto(Long idSlot, String name, String period) {
		this.idSlot = idSlot;
		this.name = name;
		this.period = period;
	}

	public Long getIdSlot() {
		return idSlot;
	}

	public void setIdSlot(Long idSlot) {
		this.idSlot = idSlot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	
	
}
