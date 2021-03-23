package org.example.backend.dao;

import org.example.backend.mapper.AuthUserMapper;
import org.example.backend.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PropertySource("classpath:sql_user.properties")
public class AuthUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${user.findByUsername}")
    private String findByUsername;
    @Value("${user.saveByUsername}")
    private String saveByUsername;
    @Value("${user.deleteByUsername}")
    private String deleteByUsername;
    @Value("${user.findAll}")
    private String findAll;

    public AuthUser findByUsername(String username) {
        AuthUser authUser;
        try {
            authUser = jdbcTemplate.queryForObject(findByUsername, new AuthUserMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            authUser = null;
        }
        return authUser;
    }

    public void saveAuthUser(AuthUser authUser) {
        jdbcTemplate.update(saveByUsername,
                authUser.getUsername(),
                authUser.getPassword(),
                authUser.getActive());
    }

    public void deleteUserByUsername(String username) {
        jdbcTemplate.update(deleteByUsername, username);
    }

    public List<AuthUser> findAll() {
        return jdbcTemplate.query(findAll, new AuthUserMapper());
    }
}
