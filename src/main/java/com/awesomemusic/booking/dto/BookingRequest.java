package com.awesomemusic.booking.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookingRequest {
	
    @NotBlank(message = "Customer name cannot be blank")
    @Size(max = 128, message = "Customer name must not exceed 128 characters")
    private String customerName;
    
    private Long slotId;
    private Long roomId;
    private LocalDate bookingDate;
    
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getSlotId() {
		return slotId;
	}
	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}

}