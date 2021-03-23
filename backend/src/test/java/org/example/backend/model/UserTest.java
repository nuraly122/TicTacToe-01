package org.example.backend.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    private static final String USER_NAME = "MIKE";
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void getName() {
        user.setName("MIKE");

        assertNotNull(user);
        assertEquals(USER_NAME, user.getName());
    }
}