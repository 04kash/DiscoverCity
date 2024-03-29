package entity;

import entity.CommonUser;
import entity.Planner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Planner planner;


    @BeforeEach
    void init(){
        User user = new CommonUser("rileyfewer", "1234riley", planner);
        this.user = user;
    }

    @Test
    void getUsername() {
        assertEquals("rileyfewer", user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("1234riley", user.getPassword());
    }

    @Test
    void getPlanner() {
        assertNull(user.getPlanner());
    }
}