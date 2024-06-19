package com.example.wigellssushi.service;

import com.example.wigellssushi.entity.Room;
import com.example.wigellssushi.exceptions.ResourceNotFoundException;
import com.example.wigellssushi.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room updateRoom(Long id, Room roomDetails) {
        Optional<Room> roomOptional = roomRepository.findById(id);

        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            if (roomDetails.getName() != null) {
                room.setName(roomDetails.getName());
            }
            if (roomDetails.getMaxGuests() != 0) {
                room.setMaxGuests(roomDetails.getMaxGuests());
            }
            if (roomDetails.getEquipment() != null) {
                room.setEquipment(roomDetails.getEquipment());
            }

            Room updatedRoom = roomRepository.save(room);
            logger.info("Room with id: " + updatedRoom.getId() +  " successfully updated.");
            return updatedRoom;
        } else {
            throw new ResourceNotFoundException("Room not found with id " + id);
        }
    }
}
