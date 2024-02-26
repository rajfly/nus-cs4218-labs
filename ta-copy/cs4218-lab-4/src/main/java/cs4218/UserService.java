package cs4218;

public class UserService implements UserServiceInterface {
    private final User user;

    UserService(User user) {
        this.user = user;
    }

    @Override
    public User getUser() {
        return this.user;
    }
}
