package cs4218;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

public class UserControllerTest {
    @Test
    public void getUserName_suppliedOneUserNamedJohn_returnJohn() {
        // create a mock of the UserService interface
        UserService userService = mock(UserService.class);

        // create a User object to return from the mock
        User user = new User("John");
        when(userService.getUser()).thenReturn(user);

        // create a UserController instance using the mocked UserService
        UserController userController = new UserController(userService);

        // call the method being tested
        String userName = userController.getUserName();

        // verify that the mocked method was called with the correct argument
        verify(userService).getUser();

        // assert that the returned value is correct
        assertEquals("John", userName);
    }

    @Test
    public void getUserId_suppliedOneUserNamedJohn_returnId() {
        final UUID DEFAULT_UUID = UUID.fromString("219b0d18-0d7a-489f-a872-ef4aab6cc158");

        // mock static with try block
        try(MockedStatic<UUID> mockedStatic = Mockito.mockStatic(UUID.class)) {
            mockedStatic.when(UUID::randomUUID).thenReturn(DEFAULT_UUID);

            // mock userService
            UserService userService = mock(UserService.class);
            User user = new User("John");
            when(userService.getUser()).thenReturn(user);

            // create controller and call method being tested
            UserController userController = new UserController(userService);
            String userId = userController.getUserId();

            // verify mocked method and assert returned value
            verify(userService).getUser();
            assertEquals(DEFAULT_UUID.toString(), userId);
        }
    }
}
