package org.example.backend.mapper;

import org.example.backend.model.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getInt("id"));
        room.setName(resultSet.getString("name"));
        room.setAmountOfPeople(resultSet.getInt("amountOfPeople"));
        return room;
    }
}
