package org.example.backend.mapper;

import org.example.backend.model.AuthUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthUserMapper implements RowMapper<AuthUser> {
    @Override
    public AuthUser mapRow(ResultSet resultSet, int i) throws SQLException {
        AuthUser authUser = new AuthUser();
        authUser.setId(resultSet.getInt("id"));
        authUser.setUsername(resultSet.getString("username"));
        authUser.setPassword(resultSet.getString("password"));
        authUser.setActive(resultSet.getBoolean("active"));
        return authUser;
    }
}
