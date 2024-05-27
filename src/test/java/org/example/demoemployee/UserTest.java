package org.example.demoemployee;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUserBuilder() {
        User user = new User.Builder()
                .username("Ludvig.Masagal")
                .email("Ludvig_Masagal@gmail.com")
                .build();

        assertEquals("Ludvig.Masagal", user.getUsername());
        assertEquals("Ludvig_Masagal@gmail.com", user.getEmail());
    }
}
