package cs4218;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @Test
    public void getUserName_suppliedOneUserNamedJohn_returnJohn() {
        // create a mock of the UserService interface
        UserService userService = mock(UserService.class);

        // create a User object to return from the mock
        User user = new User(1, "John");
        when(userService.getUser(1)).thenReturn(user);

        // create a UserController instance using the mocked UserService
        UserController userController = new UserController(userService);

        // call the method being tested
        String userName = userController.getUserName(1);

        // verify that the mocked method was called with the correct argument
        verify(userService).getUser(1);

        // assert that the returned value is correct
        assertEquals("John", userName);
    }
}
