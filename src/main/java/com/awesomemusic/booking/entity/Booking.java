package com.awesomemusic.booking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.awesomemusic.booking.config.Setting;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBooking;
	
	@Column(name = "customer_name")
	@NotBlank(message = "Customer name cannot be blank")
    @Size(max = 128, message = "Customer name must not exceed 128 characters")
    private String customerName;
    
	
    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private Slot slot;
    
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    
    @Column(name = "booking_date", nullable = false, columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Setting.APP_DATE_FORMAT)
    private LocalDate bookingDate;
    
    @Column(name = "input_date", nullable = false, columnDefinition = "DATETIME(0)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Setting.APP_DATETIME_FORMAT)
    private LocalDateTime inputDate;
    
    @Column(name = "acceptance_date", columnDefinition = "DATETIME(0)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Setting.APP_DATETIME_FORMAT)
    private LocalDateTime acceptanceDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 45, nullable = false)
    private BookingStatus status;
    
    @Column(name = "booking_code", length = 14, nullable = false, unique = true)
    private String bookingCode;
    
    public Booking() {
    	super();
    }

	public Booking(
			@NotBlank(message = "Customer name cannot be blank") @Size(max = 128, message = "Customer name must not exceed 128 characters") String customerName,
			Slot slot, Room room, LocalDate bookingDate, LocalDateTime inputDate, LocalDateTime acceptanceDate,
			BookingStatus status, String bookingCode) {
		this.customerName = customerName;
		this.slot = slot;
		this.room = room;
		this.bookingDate = bookingDate;
		this.inputDate = inputDate;
		this.acceptanceDate = acceptanceDate;
		this.status = status;
		this.bookingCode = bookingCode;
	}


	public Long getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(Long idBooking) {
		this.idBooking = idBooking;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}	

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDateTime getInputDate() {
		return inputDate;
	}

	public void setInputDate(LocalDateTime inputDate) {
		this.inputDate = inputDate;
	}

	public LocalDateTime getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(LocalDateTime acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public String getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}

	
}
