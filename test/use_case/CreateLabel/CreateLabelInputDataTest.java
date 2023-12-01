package test.use_case.CreateLabel;

import entity.CommonUser;
import entity.Planner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.User;
import entity.Label;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateLabelInputDataTest {
    private User user;
    private Planner planner;
    private Label label;

    @BeforeEach
    void init(){
        this.user = new CommonUser("rileyfewer", "1234riley4321", planner);
        ArrayList locations = new ArrayList();
        locations.add("McDon");
        this.planner = new Planner();
        planner.setLabel(this.label, locations);
    }

    @Test
    void getChosenLabel() {
        assert(planner.getLabel().contains(label));
    }

    @Test
    void getPassword() {
        Assertions.assertEquals("1234riley4321", user.getPassword());
    }

    @Test
    void getUsername() {
        Assertions.assertEquals("rileyfewer", user.getUsername());
    }
}