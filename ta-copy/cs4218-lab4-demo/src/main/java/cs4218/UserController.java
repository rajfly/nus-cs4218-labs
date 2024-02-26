package cs4218;


public class UserController {
    private UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    public String getUserName(int userId) {
        User user = userService.getUser(userId);
        return user.getName();
    }
}
