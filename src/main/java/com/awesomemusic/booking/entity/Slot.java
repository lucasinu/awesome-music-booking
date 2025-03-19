package com.awesomemusic.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "slot")
public class Slot {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSlot;

    @Column(name = "name", nullable=false, unique=true)
    @Size(max = 45, message = "Name must not exceed 45 characters")
    private String name;

    @Column(name = "start_hour")
    private Float startHour;

    @Column(name = "end_hour")
    private Float endHour;
    
    public Slot() {
    	super();
    }

	public Slot(Long idSlot, @Size(max = 45, message = "Name must not exceed 45 characters") String name,
			Float startHour, Float endHour) {
		this.idSlot = idSlot;
		this.name = name;
		this.startHour = startHour;
		this.endHour = endHour;
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

	public Float getStartHour() {
		return startHour;
	}

	public void setStartHour(Float startHour) {
		this.startHour = startHour;
	}

	public Float getEndHour() {
		return endHour;
	}

	public void setEndHour(Float endHour) {
		this.endHour = endHour;
	}    
    
}
