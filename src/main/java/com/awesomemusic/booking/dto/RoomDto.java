package com.awesomemusic.booking.dto;

/**
 * Data Transfer Object for Room entity.
 */
public class RoomDto {
	private Long idRoom;
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
