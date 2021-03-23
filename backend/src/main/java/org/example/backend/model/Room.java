package org.example.backend.model;

import java.util.Objects;

public class Room {

    private Integer id;
    private String name;
    private Integer amountOfPeople;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(Integer amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(name, room.name) &&
                Objects.equals(amountOfPeople, room.amountOfPeople);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amountOfPeople);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amountOfPeople=" + amountOfPeople +
                '}';
    }
}
