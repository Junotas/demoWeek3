package org.example.demoemployee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.mockito.Mockito.*;

public class UserRegistrationServiceTest {

    private UserService userServiceMock;
    private UserRegistrationService userRegistrationService;

    @BeforeEach
    public void setUp() {
        userServiceMock = mock(UserService.class);
        userRegistrationService = new UserRegistrationService(userServiceMock);
    }

    @Test
    public void testPerformUserActions_DeleteUsers() {
        InputStream mockInputStream = new ByteArrayInputStream("delete\nno\n".getBytes());
        Scanner scanner = new Scanner(mockInputStream);

        userRegistrationService.performUserActions(scanner);

        verify(userServiceMock, times(1)).removeUser(anyString());
    }
}
