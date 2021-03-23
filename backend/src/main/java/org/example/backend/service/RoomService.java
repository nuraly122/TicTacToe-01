package org.example.backend.service;

import org.example.backend.dao.RoomDao;
import org.example.backend.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomDao roomDao;

    public Room findRoomById(Integer id){
        return roomDao.findRoomById(id);
    }

    public void updateAmountOfPeopleInRoomById(Integer amountOfPeople, Integer id) {
        roomDao.updateAmountOfPeopleInRoomById(amountOfPeople, id);
    }
}
