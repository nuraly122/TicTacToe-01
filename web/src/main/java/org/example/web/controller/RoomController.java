package org.example.web.controller;

import org.example.backend.model.Room;
import org.example.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable Integer id) {
        return roomService.findRoomById(id);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Integer id, @RequestBody Room room) {
        roomService.updateAmountOfPeopleInRoomById(room.getAmountOfPeople(), id);
        return room;
    }

}
