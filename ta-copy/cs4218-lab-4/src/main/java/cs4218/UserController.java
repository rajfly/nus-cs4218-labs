package cs4218;


public class UserController {
    private final UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    public String getUserName() {
        User user = userService.getUser();
        return user.getName();
    }

    public String getUserId() {
        User user = userService.getUser();
        return user.getId();
    }
}
