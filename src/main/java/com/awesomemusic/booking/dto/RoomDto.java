package com.awesomemusic.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for Room entity.
 */
public class RoomDto {
	private Long idRoom;
	
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 45, message = "Name must not exceed 45 characters")
	private String name;
	private String description;
	
	public RoomDto() {
		super();
	}

	public RoomDto(Long idRoom, String name, String description) {
		this.idRoom = idRoom;
		this.name = name;
		this.description = description;
	}

	public Long getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Long idRoom) {
		this.idRoom = idRoom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
