package org.example.backend.model;

import java.util.Objects;

public class User {

    private String name;
    private Integer victory;

    public User() {
    }

    public User(String name, Integer victory) {
        this.name = name;
        this.victory = victory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVictory() {
        return victory;
    }

    public void setVictory(Integer victory) {
        this.victory = victory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(victory, user.victory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, victory);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", victory=" + victory +
                '}';
    }
}
