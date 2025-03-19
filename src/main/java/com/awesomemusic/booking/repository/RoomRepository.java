package com.awesomemusic.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awesomemusic.booking.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {}

