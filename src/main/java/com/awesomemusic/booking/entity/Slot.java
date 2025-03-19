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
    private String startHour;

    @Column(name = "end_hour")
    private String endHour;
    
    public Slot() {
    	super();
    }

	public Slot(Long idSlot, @Size(max = 45, message = "Name must not exceed 45 characters") String name,
			String startHour, String endHour) {
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

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}    
    
}
