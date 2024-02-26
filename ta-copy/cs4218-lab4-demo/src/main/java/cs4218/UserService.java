package cs4218;

public class UserService implements UserServiceInterface {
    private User user;

    UserService(User user) {
        this.user = user;
    }
    @Override
    public User getUser(int id) {
        return this.user;
    }
}
