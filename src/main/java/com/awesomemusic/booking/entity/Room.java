package com.awesomemusic.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "room")
public class Room {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;

    @Column(name = "name", nullable=false, unique=true)
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 45, message = "Name must not exceed 45 characters")
    private String name;

    @Column(name = "description")
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
    
    public Room() {
    	super();
    }

	public Room(Long idRoom, @NotBlank(message = "Name cannot be blank") String name, String description) {
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
