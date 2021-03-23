package org.example.backend.dao;

import org.example.backend.mapper.RoomMapper;
import org.example.backend.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:sql_room.properties")
public class RoomDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${room.findById}")
    private String findById;
    @Value("${room.updateAmountOfPeopleById}")
    private String updateAmountOfPeopleById;

    public Room findRoomById(Integer id) {
        return jdbcTemplate.queryForObject(findById, new RoomMapper(), id);
    }

    public void updateAmountOfPeopleInRoomById(Integer amountOfPeople, Integer id) {
        jdbcTemplate.update(updateAmountOfPeopleById, amountOfPeople, id);
    }
}
